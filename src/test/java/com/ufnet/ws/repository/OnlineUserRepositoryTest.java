/*
 * @(#)OnlineUserRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ufnet.ws.AbstractTest;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository for {@link OnlineUserRepository}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
public class OnlineUserRepositoryTest extends AbstractTest {

	@Resource
	private OnlineUserRepository onlineUserRepository;

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void select() {
		String loginName = onlineUserRepository.select("0");
		log.debug(loginName);
	}

}
