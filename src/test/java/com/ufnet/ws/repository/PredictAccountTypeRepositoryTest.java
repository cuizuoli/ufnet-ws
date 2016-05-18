/*
 * @(#)PredictAccountTypeRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.PredictAccountType;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository for predict_account_type.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
public class PredictAccountTypeRepositoryTest extends AbstractTest {

	@Resource
	private PredictAccountTypeRepository predictAccountTypeRepository;

	@Test
	public void selectList() {
		PredictAccountType predictAccountType = predictAccountTypeRepository.select("test");
		log.debug(predictAccountType != null ? predictAccountType.toString() : "");
	}

	@Test
	public void update() {
		PredictAccountType predictAccountType = new PredictAccountType();
		predictAccountType.setLoginName("test");
		predictAccountType.setImplementDate(DateTime.now());
		predictAccountType.setTargetTypeId(1);
		predictAccountTypeRepository.update(predictAccountType);
	}

}
