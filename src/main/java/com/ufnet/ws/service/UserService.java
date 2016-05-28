/*
 * @(#)UserService.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.ufnet.ws.SimpleConstants;
import com.ufnet.ws.model.AccountType;
import com.ufnet.ws.model.GroupMap;
import com.ufnet.ws.model.PauseRule;
import com.ufnet.ws.model.PredictAccountType;
import com.ufnet.ws.model.UseHistory;
import com.ufnet.ws.model.UserInfo;
import com.ufnet.ws.model.UserIpMac;
import com.ufnet.ws.repository.AccountTypeRepository;
import com.ufnet.ws.repository.GroupMapRepository;
import com.ufnet.ws.repository.OnlineUserRepository;
import com.ufnet.ws.repository.PauseRuleRepository;
import com.ufnet.ws.repository.PredictAccountTypeRepository;
import com.ufnet.ws.repository.UseHistoryRepository;
import com.ufnet.ws.repository.UserInfoRepository;
import com.ufnet.ws.repository.UserIpMacRepository;
import com.ufnet.ws.server.model.CardChangePWDRequest;
import com.ufnet.ws.server.model.CardDelUserRequest;
import com.ufnet.ws.server.model.CardNewUserRequest;
import com.ufnet.ws.server.model.GetPrePolicyListRequest;
import com.ufnet.ws.server.model.GetUserIdFromOnlineRequest;
import com.ufnet.ws.server.model.GetUserIdFromTicketRequest;
import com.ufnet.ws.server.model.GetUserInfoRequest;
import com.ufnet.ws.server.model.GetUserLimitEndDateRequest;
import com.ufnet.ws.server.model.GetUserPassWordRequest;
import com.ufnet.ws.server.model.ModifyPrePolicyRequest;
import com.ufnet.ws.server.model.ModifyUserInfoRequest;
import com.ufnet.ws.server.model.OffLineUserRequest;
import com.ufnet.ws.server.model.ResumeUserInfoRequest;
import com.ufnet.ws.utils.JodaDateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for user operation.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
@Service
public class UserService {

	private static final Map<String, Integer> STATE_MAP = new HashMap<String, Integer>();

	/**
	 * STATE_MAP.put("{userstate}", {currentState});
	 * usersate:     1 - 正常, 2 - 删除
	 * currentState: 1 - 开户, 2 - 开通, 3 - 暂停, 4 - 试用, 5 - 退网, 6 - 断网, 7 - 潜在
	 * 
	 */
	static {
		STATE_MAP.put("1", 2);
		STATE_MAP.put("2", 5);
	}

	@Resource
	private UserInfoRepository userInfoRepository;

	@Resource
	private AccountTypeRepository accountTypeRepository;

	@Resource
	private UserIpMacRepository userIpMacRepository;

	@Resource
	private GroupMapRepository groupMapRepository;

	@Resource
	private OnlineUserRepository onlineUserRepository;

	@Resource
	private UseHistoryRepository useHistoryRepository;

	@Resource
	private PauseRuleRepository pauseRuleRepository;

	@Resource
	private PredictAccountTypeRepository predictAccountTypeRepository;

	/**
	 * cardNewUser
	 * @param request
	 * @return
	 */
	public int cardNewUser(CardNewUserRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		UserInfo user = userInfoRepository.select(userId);
		if (user != null) {
			log.error("userid[" + userId + "] exists.");
			return -1;
		}
		int groupId = request.getGroupId();
		int accountTypeId = 0;
		GroupMap groupMap = groupMapRepository.selectByGroupId(groupId);
		if (groupMap != null) {
			accountTypeId = groupMap.getAccountTypeId();
		} else {
			AccountType accountType = accountTypeRepository.select(groupId);
			if (accountType != null) {
				accountTypeId = groupId;
			} else {
				log.error("groupid[" + groupId + "] invalid.");
				return 0;
			}
		}
		int teamId = request.getTeamId();
		String pwd = request.getPwd();
		if (StringUtils.isBlank(pwd)) {
			log.warn("pwd[" + pwd + "] is blank. using default pwd[" + SimpleConstants.DEFAULT_PASSWORD + "]");
			request.setPwd(SimpleConstants.DEFAULT_PASSWORD);
		}
		String userName = request.getUsername();
		if (StringUtils.isBlank(userName)) {
			userName = userId;
		}
		String phone = request.getPhone();
		//		String address = request.getAddress();
		String limitDateEnd = request.getLimitDateEnd();
		if (StringUtils.isBlank(limitDateEnd)) {
			log.error("limitDateEnd[" + limitDateEnd + "] is blank.");
			return 0;
		}
		String userState = request.getUserState();
		if (StringUtils.isBlank(userState)) {
			log.error("userstate[" + userState + "] is blank.");
			return 0;
		}
		int currentState = 0;
		if (STATE_MAP.containsKey(userState)) {
			currentState = STATE_MAP.get(userState);
		} else {
			log.error("userstate[" + userState + "] invalid.");
			return 0;
		}
		String openDate = request.getOpenDate();
		if (StringUtils.isBlank(openDate)) {
			log.error("opendate[" + openDate + "] is blank.");
			return 0;
		}
		String notes = request.getNotes();
		String certNum = request.getCertNum();
		//		if (StringUtils.isBlank(certNum)) {
		//			log.error("certNum[" + certNum + "] is blank.");
		//			return 0;
		//		}
		//		if (StringUtils.length(certNum) != 15
		//			&& StringUtils.length(certNum) != 18) {
		//			log.error("Certificate no[" + certNum + "] invalid.");
		//			return 0;
		//		}
		// Process
		int returnCode = 1;
		try {
			DateTime date = DateTime.now();
			int accountId = userInfoRepository.seq();
			String loginName = userId;
			String password = pwd;
			int payAccountId = accountId;
			AccountType accountType = accountTypeRepository.select(accountTypeId);
			if (accountType == null) {
				log.error("accounttype_id[" + accountTypeId + "] invalid.");
				return 0;
			}
			int authTypeId = accountType.getTypeId();
			int bandwidth = accountType.getMaxBand();
			DateTime dateOfEnd = JodaDateUtil.parseDate(limitDateEnd);
			if (dateOfEnd == null) {
				log.error("limitdate_end[" + limitDateEnd + "] invalid.");
				return 0;
			}
			DateTime dateOfKaitong = JodaDateUtil.parseDate(openDate);
			DateTime lastLoginTime = date;
			DateTime birthday = date;
			DateTime dateOfApply = date;
			DateTime dateOfOpen = date;
			DateTime dateOfSignContract = date;
			DateTime dateOfEndContract = date;
			int adminId = teamId;
			int openAdmin = teamId;
			String remark = notes;
			String certificateNo = certNum;

			// UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setAccountId(accountId);
			userInfo.setUserName(userName);
			userInfo.setLoginName(loginName);
			userInfo.setPassword(password);
			userInfo.setAccountTypeId(accountTypeId);
			userInfo.setAuthTypeId(authTypeId);
			userInfo.setDateOfEnd(dateOfEnd);
			userInfo.setBandwidth(bandwidth);
			userInfo.setCurrentState(currentState);
			userInfo.setDateOfKaitong(dateOfKaitong);
			userInfo.setLastLoginTime(lastLoginTime);
			userInfo.setBirthday(birthday);
			userInfo.setPhone(phone);
			userInfo.setCertificateNo(certificateNo);
			userInfo.setDateOfApply(dateOfApply);
			userInfo.setDateOfOpen(dateOfOpen);
			userInfo.setDateOfSignContract(dateOfSignContract);
			userInfo.setDateOfEndContract(dateOfEndContract);
			userInfo.setAdminId(adminId);
			userInfo.setPayAccountId(payAccountId);
			userInfo.setRemark(remark);
			userInfo.setOpenAdmin(openAdmin);
			userInfoRepository.insert(userInfo);

			// insert user_ip_mac
			UserIpMac userIpMac = new UserIpMac();
			userIpMac.setAccountId(accountId);
			userIpMacRepository.insert(userIpMac);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * cardDelUser
	 * @param request
	 * @return
	 */
	public int cardDelUser(CardDelUserRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return -1;
		}
		// Process
		int returnCode = -1;
		try {
			// 查询余额
			UserInfo userInfo = userInfoRepository.select(userId);
			if (userInfo != null) {
				returnCode = new BigDecimal(userInfo.getAvailableAmount(), new MathContext(0, RoundingMode.FLOOR)).toBigInteger().intValue();
				// 销户
				userInfoRepository.delete(userId);
				userIpMacRepository.delete(userInfo.getAccountId());
			} else {
				log.error("userid[" + userId + "] not exists.");
				return -1;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * cardChangePWD
	 * @param request
	 * @return
	 */
	public int cardChangePWD(CardChangePWDRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		UserInfo userInfo = userInfoRepository.select(userId);
		if (userInfo == null) {
			log.error("userid[" + userId + "] not exists.");
			return -1;
		}
		String destPwd = request.getDestPwd();
		if (StringUtils.isBlank(userId)) {
			log.error("destpwd[" + destPwd + "] is blank.");
			return 0;
		}
		if (StringUtils.length(destPwd) > SimpleConstants.PASSWORD_MAX_LENGTH) {
			log.error("destpwd[" + destPwd + "] over " + SimpleConstants.PASSWORD_MAX_LENGTH + ".");
			return 0;
		}
		// Process
		int returnCode = 1;
		try {
			UserInfo data = new UserInfo();
			data.setLoginName(userId);
			data.setPassword(destPwd);
			userInfoRepository.update(data);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getUserPassWord
	 * @param request
	 * @return
	 */
	public String getUserPassWord(GetUserPassWordRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		String password = "";
		try {
			UserInfo userInfo = userInfoRepository.select(userId);
			if (userInfo != null) {
				password = userInfo.getPassword();
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return password;
	}

	/**
	 * getUserLimitEndDate
	 * @param request
	 * @return
	 */
	public String getUserLimitEndDate(GetUserLimitEndDateRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		String dateOfEnd = "";
		try {
			UserInfo userInfo = userInfoRepository.select(userId);
			if (userInfo != null) {
				dateOfEnd = JodaDateUtil.formatDate(userInfo.getDateOfEnd());
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return dateOfEnd;
	}

	/**
	 * getUserInfo
	 * @param request
	 * @return
	 */
	public String getUserInfo(GetUserInfoRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return "";
		}
		// Process
		StringBuffer result = new StringBuffer();
		try {
			UserInfo userInfo = userInfoRepository.select(userId);
			if (userInfo != null) {
				String loginName = userInfo.getLoginName();
				int accountTypeId = userInfo.getAccountTypeId();
				GroupMap groupMap = groupMapRepository.selectByAccountTypeId(accountTypeId);
				int groupId = 0;
				if (groupMap != null) {
					groupId = groupMap.getGroupId();
				} else {
					groupId = accountTypeId;
				}
				String userName = userInfo.getUserName();
				String password = userInfo.getPassword();
				String phone = userInfo.getPhone();
				DateTime dateOfEnd = userInfo.getDateOfEnd();
				DateTime dateOfOpen = userInfo.getDateOfOpen();
				String remark = userInfo.getRemark();
				String address = " ";
				float availableAmount = userInfo.getAvailableAmount();
				int currentState = userInfo.getCurrentState();
				String userState = "";
				Iterator<Entry<String, Integer>> stateIterator = STATE_MAP.entrySet().iterator();
				while (stateIterator.hasNext()) {
					Entry<String, Integer> entry = stateIterator.next();
					if (entry.getValue() == currentState) {
						userState = entry.getKey();
						break;
					}
				}
				AccountType accountType = accountTypeRepository.select(accountTypeId);
				int payType = accountType.getPayType();
				result.append(loginName).append("##").append(groupId).append("##").append(userName).append("##").append(password).append("##").append(address).append("##").append(phone).append("##").append(JodaDateUtil.formatDate(dateOfEnd)).append("##").append(JodaDateUtil.formatDate(dateOfOpen)).append("##").append(remark).append("##").append(availableAmount).append("##").append(userState).append("##").append(payType);
			} else {
				log.error("userid[" + userId + "] not exists.");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return result.toString();
	}

	/**
	 * modifyUserInfo
	 * @param request
	 * @return
	 */
	public int modifyUserInfo(ModifyUserInfoRequest request) {
		// Request && Validation
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		UserInfo user = userInfoRepository.select(userId);
		if (user == null) {
			log.error("userid[" + userId + "] not exists.");
			return 0;
		}
		int groupId = request.getGroupId();
		int accountTypeId = 0;
		if (groupId != 0) {
			GroupMap groupMap = groupMapRepository.selectByGroupId(groupId);
			if (groupMap != null) {
				accountTypeId = groupMap.getAccountTypeId();
			} else {
				AccountType accountType = accountTypeRepository.select(groupId);
				if (accountType != null) {
					accountTypeId = groupId;
				} else {
					log.error("groupid[" + groupId + "] invalid.");
					return 0;
				}
			}
		}
		int teamId = request.getTeamId();
		String pwd = request.getPwd();
		if (StringUtils.isBlank(pwd)) {
			log.error("pwd[" + pwd + "] is blank. using default pwd[" + SimpleConstants.DEFAULT_PASSWORD + "]");
			request.setPwd(SimpleConstants.DEFAULT_PASSWORD);
		}
		String userName = request.getUsername();
		String phone = request.getPhone();
		//		String address = request.getAddress();
		String limitDateEnd = request.getLimitDateEnd();
		String userState = request.getUserState();
		int currentState = 0;
		if (StringUtils.isNotBlank(userState)) {
			if (STATE_MAP.containsKey(userState)) {
				currentState = STATE_MAP.get(userState);
			} else {
				log.error("userstate[" + userState + "] invalid.");
				return 0;
			}
		}
		String openDate = request.getOpenDate();
		String notes = request.getNotes();
		float remainFee = request.getRemainFee();
		String certNum = request.getCertNum();
		// Process
		int returnCode = 1;
		try {
			String loginName = userId;
			String password = pwd;
			DateTime dateOfEnd = null;
			if (StringUtils.isNotEmpty(limitDateEnd)) {
				dateOfEnd = JodaDateUtil.parseDate(limitDateEnd);
				if (dateOfEnd == null) {
					log.error("limitdate_end[" + limitDateEnd + "] invalid.");
					return 0;
				}
			}
			DateTime dateOfKaitong = null;
			if (StringUtils.isNotEmpty(openDate)) {
				dateOfKaitong = JodaDateUtil.parseDate(openDate);
				if (dateOfKaitong == null) {
					log.error("opendate[" + openDate + "] invalid.");
					return 0;
				}
			}
			int adminId = teamId;
			int openAdmin = teamId;
			String remark = notes;
			String certificateNo = certNum;
			int authTypeId = 0;
			int bandwidth = 0;
			if (accountTypeId != 0) {
				AccountType accountType = accountTypeRepository.select(accountTypeId);
				authTypeId = accountType.getTypeId();
				bandwidth = accountType.getMaxBand();
			}

			// UserInfo
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			userInfo.setLoginName(loginName);
			userInfo.setPassword(password);
			userInfo.setAvailableAmount(remainFee);
			userInfo.setAccountTypeId(accountTypeId);
			userInfo.setAuthTypeId(authTypeId);
			userInfo.setBandwidth(bandwidth);
			userInfo.setDateOfEnd(dateOfEnd);
			userInfo.setCurrentState(currentState);
			userInfo.setDateOfKaitong(dateOfKaitong);
			userInfo.setPhone(phone);
			userInfo.setCertificateNo(certificateNo);
			userInfo.setAdminId(adminId);
			userInfo.setRemark(remark);
			userInfo.setOpenAdmin(openAdmin);

			// update user_info
			userInfoRepository.update(userInfo);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getUserIdFromOnline
	 * @param request
	 * @return
	 */
	public String getUserIdFromOnline(GetUserIdFromOnlineRequest request) {
		// Request && Validation
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("Userip[" + userIp + "] is blank.");
			return "";
		}
		try {
			String loginName = onlineUserRepository.select(userIp);
			if (StringUtils.isNotEmpty(loginName)) {
				return loginName;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return "";
	}

	/**
	 * offLineUser
	 * @param request
	 * @return
	 */
	public int offLineUser(OffLineUserRequest request) {
		// Request && Validation
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("userip[" + userIp + "] is blank.");
			return 0;
		}
		String userMac = request.getUserMac();
		if (StringUtils.isBlank(userIp)) {
			log.error("usermac[" + userMac + "] is blank.");
			return 0;
		}
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		// Process
		int returnCode = 1;
		try {
			onlineUserRepository.update(userId, userIp, userMac);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			returnCode = 0;
		}
		return returnCode;
	}

	/**
	 * getUserIdFromTicket
	 * @param request
	 * @return
	 */
	public String getUserIdFromTicket(GetUserIdFromTicketRequest request) {
		String userIp = request.getUserIp();
		if (StringUtils.isBlank(userIp)) {
			log.error("userip[" + userIp + "] is blank.");
			return "";
		}
		String onlineTime = request.getOnlineTime();
		if (StringUtils.isBlank(onlineTime)) {
			log.error("onlinetime[" + onlineTime + "] is blank.");
			return "";
		}
		try {
			UseHistory data = new UseHistory();
			data.setIpAddress(userIp);
			data.setOnlineTime(JodaDateUtil.parseDateTime(onlineTime));
			String loginName = useHistoryRepository.select(userIp, JodaDateUtil.parseDateTime(onlineTime));
			if (StringUtils.isNotEmpty(loginName)) {
				return loginName;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return "";
	}

	/**
	 * resumeUserInfo
	 * @param request
	 * @return
	 */
	public int resumeUserInfo(ResumeUserInfoRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userid[" + userId + "] is blank.");
			return 0;
		}
		UserInfo user = userInfoRepository.select(userId);
		if (user == null) {
			log.error("userid[" + userId + "] not exists.");
			return 0;
		}
		int returnCode = 1;
		try {
			UserInfo userInfo = userInfoRepository.select(userId);
			AccountType accountType = accountTypeRepository.select(userInfo.getAccountTypeId());
			PauseRule pauseRule = pauseRuleRepository.select(accountType.getPauseRuleId());
			float availableAmount = 0;
			if (pauseRule != null) {
				availableAmount = userInfo.getAvailableAmount() - pauseRule.getPauseFee();
				if (availableAmount < 0) {
					log.error("availableAmount[" + userInfo.getAvailableAmount() + "] < pauseFee["
						+ pauseRule.getPauseFee() + "].");
					return 0;
				}
			}
			UserInfo data = new UserInfo();
			data.setLoginName(userId);
			data.setCurrentState(SimpleConstants.STATE_OPEN);
			data.setAvailableAmount(availableAmount);
			userInfoRepository.update(data);
		} catch (Exception e) {
			returnCode = 0;
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return returnCode;
	}

	/**
	 * getPrePolicyList
	 * @param request
	 * @return
	 */
	public String getPrePolicyList(GetPrePolicyListRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userID[" + userId + "] is blank.");
			return "";
		}
		try {
			PredictAccountType predictAccountType = predictAccountTypeRepository.select(userId);
			if (predictAccountType != null) {
				return new StringBuffer().append(predictAccountType.getOriginTypeId()).append("==").append(predictAccountType.getOriginTypeName()).append("#####").append(predictAccountType.getTargetTypeId()).append("==").append(predictAccountType.getTargetTypeName()).toString();
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return "";
	}

	/**
	 * modifyPrePolicy
	 * @param request
	 * @return
	 */
	public boolean modifyPrePolicy(ModifyPrePolicyRequest request) {
		String userId = request.getUserId();
		if (StringUtils.isBlank(userId)) {
			log.error("userID[" + userId + "] is blank.");
			return false;
		}
		int userGroupId = request.getUserGroupId();
		int accountTypeId = 0;
		GroupMap data = new GroupMap();
		data.setGroupId(userGroupId);
		GroupMap groupMap = groupMapRepository.selectByGroupId(userGroupId);
		if (groupMap != null) {
			accountTypeId = groupMap.getAccountTypeId();
		} else {
			log.error("userGroupID[" + userGroupId + "] invalid.");
			return false;
		}
		String prDate = request.getPrDate();
		if (StringUtils.isBlank(prDate)) {
			log.error("prDate[" + prDate + "] is blank.");
			return false;
		}
		DateTime prDateTime = JodaDateUtil.parseDate(prDate);
		if (prDateTime == null) {
			log.error("prDate[" + prDate + "] invalid.");
			return false;
		}
		try {
			PredictAccountType predictAccountType = new PredictAccountType();
			predictAccountType.setLoginName(userId);
			predictAccountType.setTargetTypeId(accountTypeId);
			predictAccountType.setImplementDate(JodaDateUtil.parseDate(prDate));
			int i = predictAccountTypeRepository.update(predictAccountType);
			if (i == 0) {
				return false;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		return true;
	}

}
