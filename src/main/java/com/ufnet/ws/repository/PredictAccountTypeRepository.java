/*
 * @(#)PredictAccountTypeRepository.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ufnet.ws.model.PredictAccountType;

/**
 * Repository for predict_account_type.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Mapper
public interface PredictAccountTypeRepository {
	/**
	 * selectList
	 * @param loginName
	 * @return
	 */
	List<PredictAccountType> selectList(String loginName);

	/**
	 * update
	 * @param predictAccountType
	 */
	void update(PredictAccountType predictAccountType);
}
