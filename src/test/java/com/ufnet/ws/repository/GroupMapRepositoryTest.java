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
	public void select() {
		GroupMap groupMap = new GroupMap();
		groupMap.setGroupId(12);
		GroupMap groupMap1 = groupMapRepository.select(groupMap);
		log.debug(groupMap1 != null ? groupMap1.toString() : "");
		groupMap = new GroupMap();
		groupMap.setAccounttypeId(1);
		GroupMap groupMap2 = groupMapRepository.select(groupMap);
		log.debug(groupMap2 != null ? groupMap2.toString() : "");
	}

}
