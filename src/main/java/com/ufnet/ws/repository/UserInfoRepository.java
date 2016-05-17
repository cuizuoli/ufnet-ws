/*
 * @(#)UserInfoRepository.java $version 2016年5月17日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ufnet.ws.model.UserInfo;

/**
 * Repository for UserInfo operation.
 * @author cuizuoli
 * @date 2016年5月17日
 */
@Mapper
public interface UserInfoRepository {
	/**
	 * selectList
	 * @return
	 */
	List<UserInfo> selectList();

	/**
	 * select
	 * @param loginName
	 * @return
	 */
	UserInfo select(String loginName);

	/**
	 * insert
	 * @param userInfo
	 * @return
	 */
	int insert(UserInfo userInfo);

	/**
	 * update
	 * @param userInfo
	 * @return
	 */
	int update(UserInfo userInfo);

	/**
	 * delete
	 * @param loginName
	 * @return
	 */
	int delete(String loginName);

	/**
	 * seq
	 * @return
	 */
	int seq();
}
