/*
 * @(#)GroupMapRepository.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import org.apache.ibatis.annotations.Mapper;

import com.ufnet.ws.model.GroupMap;

/**
 * Repository for group_map.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Mapper
public interface GroupMapRepository {
	/**
	 * select
	 * @param groupMap
	 * @return
	 */
	GroupMap select(GroupMap groupMap);
}
