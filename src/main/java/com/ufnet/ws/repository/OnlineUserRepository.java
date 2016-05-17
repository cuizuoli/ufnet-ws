/*
 * @(#)OnlineUserRepository.java $version 2016年5月17日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

/**
 * Repository for onlineusertbl.
 * @author cuizuoli
 * @date 2016年5月17日
 */
//@Mapper
public interface OnlineUserRepository {
	/**
	 * select login name.
	 * @param userIp
	 * @return
	 */
	String select(String userIp);
}
