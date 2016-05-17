/*
 * @(#)OnlineUserRepository.java $version 2016年5月17日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Repository for onlineusertbl.
 * @author cuizuoli
 * @date 2016年5月17日
 */
@Mapper
public interface OnlineUserRepository {
	/**
	 * select login name.
	 * @param userIp
	 * @return
	 */
	String select(String userIp);

	/**
	 * update
	 * @param loginName
	 * @param ipAddress
	 * @param macAddress
	 * @return
	 */
	int update(
			@Param("loginName") String loginName,
			@Param("ipAddress") String ipAddress,
			@Param("macAddress") String macAddress);
}
