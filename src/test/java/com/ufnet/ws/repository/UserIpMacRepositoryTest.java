/*
 * @(#)UserIpMacRepositoryTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.repository;

import javax.annotation.Resource;

import org.junit.Test;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.model.UserIpMac;

/**
 * TestCase for {@link UserIpMacRepository}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
public class UserIpMacRepositoryTest extends AbstractTest {

	@Resource
	private UserIpMacRepository userIpMacRepository;

	@Test
	public void insert() {
		UserIpMac userIpMac = new UserIpMac();
		userIpMac.setAccountId(1);
		userIpMac.setIpAddress("0");
		userIpMac.setMac("0");
		userIpMac.setGatewayId(1);
		userIpMacRepository.insert(userIpMac);
	}

	@Test
	public void delete() {
		UserIpMac userIpMac = new UserIpMac();
		userIpMac.setAccountId(1);
		userIpMac.setIpAddress("0");
		userIpMac.setMac("0");
		userIpMac.setGatewayId(1);
		userIpMacRepository.insert(userIpMac);
		userIpMacRepository.delete(1);
	}

}
