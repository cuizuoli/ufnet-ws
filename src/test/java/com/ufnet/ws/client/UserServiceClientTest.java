/*
 * @(#)UserServiceClientTest.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.ufnet.ws.AbstractTest;
import com.ufnet.ws.server.model.CardDelUserRequest;

/**
 * TestCase for {@link UserServiceClient}.
 * @author cuizuoli
 * @date 2016年5月18日
 */
public class UserServiceClientTest extends AbstractTest {

	@Resource
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void cardDelUser() {
		CardDelUserRequest request = new CardDelUserRequest();
		webServiceTemplate.marshalSendAndReceive("http://http://124.67.20.216/:8080/services/CardCharge", request);
	}

}
