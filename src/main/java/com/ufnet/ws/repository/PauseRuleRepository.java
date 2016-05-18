/*
 * @(#)PauseRuleRepository.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import org.apache.ibatis.annotations.Mapper;

import com.ufnet.ws.model.PauseRule;

/**
 * Repository for pause_rule.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Mapper
public interface PauseRuleRepository {
	/**
	 * select
	 * @param pauseRuleId
	 * @return
	 */
	PauseRule select(int pauseRuleId);
}
