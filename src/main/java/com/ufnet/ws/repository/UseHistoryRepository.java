/*
 * @(#)UseHistoryRepository.java $version 2016年5月17日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import org.apache.ibatis.annotations.Param;

/**
 * Repository for use_history.
 * @author cuizuoli
 * @date 2016年5月17日
 */
//@Mapper
public interface UseHistoryRepository {
	/**
	 * select
	 * @param ipAddress
	 * @param onlineTime
	 * @return
	 */
	String select(@Param("ipAddress") String ipAddress, @Param("onlineTime") String onlineTime);
}
