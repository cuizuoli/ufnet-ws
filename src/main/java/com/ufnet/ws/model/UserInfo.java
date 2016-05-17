/*
 * @(#)UserInfo.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.model;

import org.joda.time.DateTime;

import lombok.Data;

/**
 * ufnet-ws
 * com.ufnet.ws.model.UserInfo.java
 * @author st13902
 * @date 2013-5-14
 */
@Data
public class UserInfo {
	// 客服中心
	// center.center_id=22
	private int accountId;
	private String userName;
	// 所属小区
	private int communityId = 1;
	// 楼号
	// comm_building.building_id=22
	// 门牌号
	private String aptNo = "";
	private String loginName;
	private String password;
	private int accountTypeId;
	private int authTypeId;
	// 认证漫游 0 - 否 1 - 是
	private int authMove = 0;
	private DateTime dateOfEnd;
	// 可用金额 单位：元
	private float availableAmount = 0;
	// 可用时长 单位：秒 -1 表示不限制
	private int availableTime = -1;
	// 可用流量 单位：KB -1 表示不限制
	private int availableFlow = -1;
	private int bandwidth;
	// 已用金额 单位：元
	private int usedAmount = 0;
	// 已用流量 单位：KB
	private int usedFlow = 0;
	// 已用时长 单位：秒
	private int usedTime = 0;
	// 最大上网时长 单位：秒 -1 表示不限制
	private int maxNetTime = -1;
	// 自动断网间隔 单位：秒 -1 表示不限制
	private int autoDownInterval = -1;
	// 弹出在线窗口 0 - 不弹出 1 - 弹出
	private int isPopup = 0;
	// 真实IP地址
	private String needRealIp = "";
	// 记录日至 0 - 不记录 1 - 记录
	private int isLog = 1;
	private int currentState;
	private DateTime dateOfKaitong;
	private DateTime lastLoginTime;
	// 登录SessionID
	private int loginSessionId = 0;
	// 最大在线用户数
	private int maxOnlineUsers = 1;
	// 性别
	private int sex = 1;
	private DateTime birthday;
	private String phone;
	// 手机
	private String mobilePhone = "";
	// 电子邮件
	private String email = "";
	// 文化程度ID 1 - 小学 2 - 初中 3 - 高中 4 - 大专 5 - 大本 6 - 硕士 7 - 博士 8 - 其它
	private int educationLevelId = 8;
	// 职业ID
	// 1  - 金融保险		2  - 交通运输 	3  - 邮电通讯	4  - 制造 		5  - 计算机技术
	// 6  - 进出口贸易	7  - 军工 		8  - 出版印刷 	9  - 科研 		10 - 教育
	// 11 - 医药卫生 		12 - 石油化工 	13 - 政府机关 	14 - 旅游 		15 - 广播电视
	// 16 - 建筑业 		17 - 农业种植 	18 - 渔牧副业  	19 - 艺术设计 	20 - 法律相关行业
	// 21 - 自由职业 		22 - 营销中介 	23 - 学生 		24 - 其它行业 
	private int vocationId = 24;
	// 用户类型 1 - 小区用户 2 - 内部用户 3 - 公司
	private int userClass = 2;
	// VIP用户 0 - 非VIP用户 1 - VIP用户
	private int vip = 0;
	// 报名类型 2 - 现场报名 3 - 电话报名 4 - 网络报名 5 - 物业推荐 6 - 柜台报名 7 - 其它
	private int registerType = 7;
	// 证件类型 1 - 身份证 2 - 军官证 3 - 护照 4 - 学生证 5 - 其它
	private int certificateTypeId = 1;
	private String certificateNo;
	private DateTime dateOfApply;
	private DateTime dateOfOpen;
	private DateTime dateOfSignContract;
	private DateTime dateOfEndContract;
	// 网络设备是否安装 0 - 未安装 1 - 已安装
	private int isInstall = 1;
	// 是否布线 0 - 未布线 1 - 已布线
	private int isWiring = 1;
	// VLan ID
	private int vlanId = 0;
	// 交换机编号
	private String switchId = "";
	// 交换机端口
	private int switchPort = 0;
	// 业务员
	private String sales = "WSDL";
	private int adminId;
	// 楼号
	private String buildingNo = "";
	// 不安装原因 1 - 没有布线 2 - 暂不安装 3 - 不安装 4 - 信息错误 5 - 其他原因
	private int reasonOfNoInstall = 0;
	// 难缠指数
	private int complexIndex = 0;
	private int payAccountId;
	// 独立帐号 0 - 非独立帐号 1 - 独立帐号
	private int independent = 1;
	private String remark;
	// 0: 禁止 1:允许
	private int denyAgent = 1;
	// 1 - web 2 - client 3 - web + client
	private int loginType = 3;
	private int openAdmin;
	private int secondLine = 0;
}
