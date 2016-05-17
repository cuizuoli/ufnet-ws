/*
 * @(#)UserInfoRepositoryTest.java $version 2016年5月17日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * TestCase for {@link UserInfoRepository}.
 * @author cuizuoli
 * @date 2016年5月17日
 */
@Slf4j
public class UserInfoRepositoryTest extends AbstractTest {

	@Resource
	private UserInfoRepository userInfoRepository;

	@Test
	public void selectList() {
		List<UserInfo> userInfoList = userInfoRepository.selectList();
		log.debug(userInfoList.toString());
	}

	@Test
	public void select() {
		UserInfo userInfo = userInfoRepository.select("test");
		log.debug(userInfo.toString());
	}

	@Test
	public void insert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccountId(userInfoRepository.seq());
		userInfo.setUserName("test1");
		userInfo.setLoginName("test1");
		userInfo.setPassword("test1");
		userInfo.setAccountTypeId(2);
		userInfo.setAuthTypeId(2);
		userInfo.setDateOfEnd(DateTime.now());
		userInfo.setBandwidth(1);
		userInfo.setCurrentState(1);
		userInfo.setDateOfKaitong(DateTime.now());
		userInfo.setLastLoginTime(DateTime.now());
		userInfo.setBirthday(DateTime.now());
		userInfo.setPhone("111");
		userInfo.setCertificateNo("1");
		userInfo.setDateOfApply(DateTime.now());
		userInfo.setDateOfOpen(DateTime.now());
		userInfo.setDateOfSignContract(DateTime.now());
		userInfo.setDateOfEndContract(DateTime.now());
		userInfo.setAdminId(1);
		userInfo.setPayAccountId(1);
		userInfo.setRemark("test1");
		userInfo.setOpenAdmin(1);
		userInfoRepository.insert(userInfo);
	}

	@Test
	public void update() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccountId(999999);
		userInfo.setUserName("test1");
		userInfo.setLoginName("test1");
		userInfo.setPassword("test1");
		userInfo.setAccountTypeId(2);
		userInfo.setAuthTypeId(2);
		userInfo.setDateOfEnd(DateTime.now());
		userInfo.setBandwidth(1);
		userInfo.setCurrentState(1);
		userInfo.setDateOfKaitong(DateTime.now());
		userInfo.setLastLoginTime(DateTime.now());
		userInfo.setBirthday(DateTime.now());
		userInfo.setPhone("111");
		userInfo.setCertificateNo("1");
		userInfo.setDateOfApply(DateTime.now());
		userInfo.setDateOfOpen(DateTime.now());
		userInfo.setDateOfSignContract(DateTime.now());
		userInfo.setDateOfEndContract(DateTime.now());
		userInfo.setAdminId(1);
		userInfo.setPayAccountId(1);
		userInfo.setRemark("test1");
		userInfo.setOpenAdmin(1);
		userInfoRepository.insert(userInfo);
		userInfo.setUserName("test123");
		userInfoRepository.update(userInfo);
	}

	@Test
	public void delete() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAccountId(999999);
		userInfo.setUserName("test1");
		userInfo.setLoginName("test1");
		userInfo.setPassword("test1");
		userInfo.setAccountTypeId(2);
		userInfo.setAuthTypeId(2);
		userInfo.setDateOfEnd(DateTime.now());
		userInfo.setBandwidth(1);
		userInfo.setCurrentState(1);
		userInfo.setDateOfKaitong(DateTime.now());
		userInfo.setLastLoginTime(DateTime.now());
		userInfo.setBirthday(DateTime.now());
		userInfo.setPhone("111");
		userInfo.setCertificateNo("1");
		userInfo.setDateOfApply(DateTime.now());
		userInfo.setDateOfOpen(DateTime.now());
		userInfo.setDateOfSignContract(DateTime.now());
		userInfo.setDateOfEndContract(DateTime.now());
		userInfo.setAdminId(1);
		userInfo.setPayAccountId(1);
		userInfo.setRemark("test1");
		userInfo.setOpenAdmin(1);
		userInfoRepository.insert(userInfo);
		userInfoRepository.delete("test1");
	}

	@Test
	public void seq() {
		int seq = userInfoRepository.seq();
		log.debug(String.valueOf(seq));
	}

}
