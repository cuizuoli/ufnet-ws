/*
 * @(#)PredictAccountTypeRepository.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

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
	 * select
	 * @param loginName
	 * @return
	 */
	PredictAccountType select(String loginName);

	/**
	 * update
	 * @param predictAccountType
	 */
	int update(PredictAccountType predictAccountType);
}
