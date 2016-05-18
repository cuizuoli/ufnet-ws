/*
 * @(#)AccountTypeRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.AccountType;

import lombok.extern.slf4j.Slf4j;

/**
 * TestCase for {@link AccountTypeRepository}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
public class AccountTypeRepositoryTest extends AbstractTest {

	@Resource
	private AccountTypeRepository accountTypeRepository;

	@Test
	public void select() {
		AccountType accountType = accountTypeRepository.select(1);
		log.debug(accountType != null ? accountType.toString() : "");
	}

}
