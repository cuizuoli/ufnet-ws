/*
 * @(#)GroupMapRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.GroupMap;

import lombok.extern.slf4j.Slf4j;

/**
 * TestCase for {@link GroupMapRepository}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@Slf4j
public class GroupMapRepositoryTest extends AbstractTest {

	@Resource
	private GroupMapRepository groupMapRepository;

	@Test
	public void selectByGroupId() {
		GroupMap groupMap = groupMapRepository.selectByGroupId(12);
		log.debug(groupMap != null ? groupMap.toString() : "");
	}

	@Test
	public void selectByAccountTypeId() {
		GroupMap groupMap = groupMapRepository.selectByAccountTypeId(1);
		log.debug(groupMap != null ? groupMap.toString() : "");
	}

}
