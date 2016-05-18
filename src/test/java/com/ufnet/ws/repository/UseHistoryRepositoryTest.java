/*
 * @(#)UseHistoryRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;

import com.ufnet.ws.AbstractTest;

/**
 * TestCase for {@link UseHistoryRepository}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
public class UseHistoryRepositoryTest extends AbstractTest {

	@Resource
	private UseHistoryRepository useHistoryRepository;

	@Test
	public void select() {
		useHistoryRepository.select("0", DateTime.now());
	}

}
