/*
 * @(#)PauseRuleRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.PauseRule;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository for pause_rule.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
public class PauseRuleRepositoryTest extends AbstractTest {

	@Resource
	private PauseRuleRepository pauseRuleRepository;

	@Test
	public void select() {
		PauseRule pauseRule = pauseRuleRepository.select(1);
		log.debug(pauseRule != null ? pauseRule.toString() : "");
	}

}
