/*
 * @(#)WebServiceTemplateTest.java $version 2016年5月19日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.server.model.CardNewUserRequest;
import com.ufnet.ws.server.model.CardNewUserResponse;

/**
 * TestCase for {@link WebServiceTemplate}.
 * @author cuizuoli
 * @date 2016年5月19日
 */
public class WebServiceTemplateTest extends AbstractTest {

	@Value("${ufnet.sync.uri}")
	private String syncUri;

	@Resource
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void cardNewUser() {
		CardNewUserRequest request = new CardNewUserRequest();
		request.setUserId("test");
		request.setGroupId(12);
		request.setTeamId(11);
		request.setPwd("654123");
		request.setUsername("崔作利");
		request.setPhone("0411-88888");
		request.setAddress("高薪园区");
		request.setLimitDateEnd("2016-10-20");
		request.setUserState("1");
		request.setOpenDate("2016-01-01");
		request.setNotes("务注Comment");
		request.setCertNum("210112198011");
		CardNewUserResponse response = (CardNewUserResponse)webServiceTemplate.marshalSendAndReceive(syncUri, request);
		System.out.println(response.getReturnCode());
	}

}