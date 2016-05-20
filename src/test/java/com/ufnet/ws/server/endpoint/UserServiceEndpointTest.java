/*
 * @(#)UserServiceEndpointTest.java $version 2013-5-14
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.endpoint;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

import com.ufnet.ws.AbstractTest;

/**
 * ufnet-ws
 * com.ufnet.ws.server.endpoint.UserServiceEndpointTest.java
 * @author st13902
 * @date 2013-5-14
 */
public class UserServiceEndpointTest extends AbstractTest {

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@Before
	public void init() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void cardNewUser() {
		Source requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31 10:30:02</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>210112198011</certNum>"
			+ "</CardNewUser>");
		Source responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>1</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// user exists
		requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</CardNewUser>");
		responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>-1</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// groupid invalid
		requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser03</userid>"
			+ "<groupid>13</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</CardNewUser>");
		responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>0</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// limitdate_end invalid
		requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser04</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>201310-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</CardNewUser>");
		responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>0</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userstate invalid
		requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser05</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>201310-31</limitdate_end>"
			+ "<userstate>8</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</CardNewUser>");
		responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>0</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userstate invalid
		requestPayload = new StringSource("<CardNewUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser06</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>201310-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<certNum>21011219853145</certNum>"
			+ "</CardNewUser>");
		responsePayload = new StringSource("<CardNewUser4Response>"
			+ "<CardNewUser4Return>0</CardNewUser4Return>"
			+ "</CardNewUser4Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void cardDelUser() {
		Source requestPayload = new StringSource("<CardDelUser xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "</CardDelUser>");
		Source responsePayload = new StringSource("<CardDelUserResponse>"
			+ "<CardDelUserReturn>0</CardDelUserReturn>"
			+ "</CardDelUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<CardDelUser xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "</CardDelUser>");
		responsePayload = new StringSource("<CardDelUserResponse>"
			+ "<CardDelUserReturn>-1</CardDelUserReturn>"
			+ "</CardDelUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void cardChangePWD() {
		Source requestPayload = new StringSource("<CardChangePWD2 xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "<destpwd>66666</destpwd>"
			+ "</CardChangePWD2>");
		Source responsePayload = new StringSource("<CardChangePWD2Response>"
			+ "<CardChangePWD2Return>1</CardChangePWD2Return>"
			+ "</CardChangePWD2Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<CardChangePWD2 xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "<destpwd>66666</destpwd>"
			+ "</CardChangePWD2>");
		responsePayload = new StringSource("<CardChangePWD2Response>"
			+ "<CardChangePWD2Return>-1</CardChangePWD2Return>"
			+ "</CardChangePWD2Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserPassWord() {
		Source requestPayload = new StringSource("<getUserPassWord xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "</getUserPassWord>");
		Source responsePayload = new StringSource("<getUserPassWordResponse>"
			+ "<getUserPassWordReturn>test</getUserPassWordReturn>"
			+ "</getUserPassWordResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<getUserPassWord xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "</getUserPassWord>");
		responsePayload = new StringSource("<getUserPassWordResponse>"
			+ "<getUserPassWordReturn></getUserPassWordReturn>"
			+ "</getUserPassWordResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserLimitEndDate() {
		Source requestPayload = new StringSource("<getUserLimitEndDate xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "</getUserLimitEndDate>");
		Source responsePayload = new StringSource("<getUserLimitEndDateResponse>"
			+ "<getUserLimitEndDateReturn>2016-07-10</getUserLimitEndDateReturn>"
			+ "</getUserLimitEndDateResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<getUserLimitEndDate xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "</getUserLimitEndDate>");
		responsePayload = new StringSource("<getUserLimitEndDateResponse>"
			+ "<getUserLimitEndDateReturn></getUserLimitEndDateReturn>"
			+ "</getUserLimitEndDateResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserInfo() {
		Source requestPayload = new StringSource("<getUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "</getUserInfo>");
		Source responsePayload = new StringSource("<getUserInfoResponse>"
			+ "<getUserInfoReturn>test##12##test##test## ##13333333333##2016-07-10##2016-03-02##null##0.0##1##0</getUserInfoReturn>"
			+ "</getUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<getUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "</getUserInfo>");
		responsePayload = new StringSource("<getUserInfoResponse>"
			+ "<getUserInfoReturn></getUserInfoReturn>"
			+ "</getUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void modifyUserInfo() {
		Source requestPayload = new StringSource("<ModifyUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-8888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31 10:30:10</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<remain_fee>Other</remain_fee>"
			+ "<certNum>2101121</certNum>"
			+ "</ModifyUserInfo>");
		Source responsePayload = new StringSource("<ModifyUserInfo3Response>"
			+ "<ModifyUserInfo3Return>1</ModifyUserInfo3Return>"
			+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// userid not exists
		requestPayload = new StringSource("<ModifyUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser02</userid>"
			+ "<groupid>12</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<remain_fee>Other</remain_fee>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</ModifyUserInfo>");
		responsePayload = new StringSource("<ModifyUserInfo3Response>"
			+ "<ModifyUserInfo3Return>0</ModifyUserInfo3Return>"
			+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		// groupid invalid
		requestPayload = new StringSource("<ModifyUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>CardNewUser01</userid>"
			+ "<groupid>3</groupid>"
			+ "<teamid>11</teamid>"
			+ "<pwd>654123</pwd>"
			+ "<username>崔作利</username>"
			+ "<phone>0411-88888888</phone>"
			+ "<address>软件园</address>"
			+ "<limitdate_end>2013-10-31</limitdate_end>"
			+ "<userstate>1</userstate>"
			+ "<opendate>2013-05-01</opendate>"
			+ "<notes>备注项目</notes>"
			+ "<remain_fee>Other</remain_fee>"
			+ "<certNum>210112198011253145</certNum>"
			+ "</ModifyUserInfo>");
		responsePayload = new StringSource("<ModifyUserInfo3Response>"
			+ "<ModifyUserInfo3Return>0</ModifyUserInfo3Return>"
			+ "</ModifyUserInfo3Response>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserIdFromOnline() {
		Source requestPayload = new StringSource("<getUserIdFromOnline xmlns=\"http://amtium.com\">"
			+ "<userip>192.168.1.102</userip>"
			+ "</getUserIdFromOnline>");
		Source responsePayload = new StringSource("<getUserIdFromOnlineResponse>"
			+ "<getUserIdFromOnlineReturn></getUserIdFromOnlineReturn>"
			+ "</getUserIdFromOnlineResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
		requestPayload = new StringSource("<getUserIdFromOnline xmlns=\"http://amtium.com\">"
			+ "<userip>192.168.1.11</userip>"
			+ "</getUserIdFromOnline>");
		responsePayload = new StringSource("<getUserIdFromOnlineResponse>"
			+ "<getUserIdFromOnlineReturn></getUserIdFromOnlineReturn>"
			+ "</getUserIdFromOnlineResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void resumeUserInfo() {
		Source requestPayload = new StringSource("<ResumeUserInfo xmlns=\"http://amtium.com\">"
			+ "<userid>test</userid>"
			+ "</ResumeUserInfo>");
		Source responsePayload = new StringSource("<ResumeUserInfoResponse>"
			+ "<ResumeUserInfoReturn>1</ResumeUserInfoReturn>"
			+ "</ResumeUserInfoResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void offLineUser() {
		Source requestPayload = new StringSource("<offLineUser xmlns=\"http://amtium.com\">"
			+ "<userip>192.168.1.102</userip>"
			+ "<usermac>10-BF-48-7A-14-DD</usermac>"
			+ "<userid>TestUser001</userid>"
			+ "</offLineUser>");
		Source responsePayload = new StringSource("<offLineUserResponse>"
			+ "<offLineUserReturn>1</offLineUserReturn>"
			+ "</offLineUserResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getUserIdFromTicket() {
		Source requestPayload = new StringSource("<getUserIdFromTicket xmlns=\"http://amtium.com\">"
			+ "<userip>192.168.1.102</userip>"
			+ "<onlinetime>2013-05-18 11:05:30</onlinetime>"
			+ "</getUserIdFromTicket>");
		Source responsePayload = new StringSource("<getUserIdFromTicketResponse>"
			+ "<getUserIdFromTicketReturn></getUserIdFromTicketReturn>"
			+ "</getUserIdFromTicketResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void getPrePolicyList() {
		Source requestPayload = new StringSource("<getPrePolicyList xmlns=\"http://amtium.com\">"
			+ "<userID>test</userID>"
			+ "</getPrePolicyList>");
		Source responsePayload = new StringSource("<getPrePolicyListResponse>"
			+ "<ReturnCode></ReturnCode>"
			+ "</getPrePolicyListResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

	@Test
	public void modifyPrePolicy() {
		Source requestPayload = new StringSource("<modifyPrePolicy xmlns=\"http://amtium.com\">"
			+ "<userID>TestUser001</userID>"
			+ "<userGroupID>12</userGroupID>"
			+ "<prDate>2013-05-21 10:30:20</prDate>"
			+ "</modifyPrePolicy>");
		Source responsePayload = new StringSource("<modifyPrePolicyResponse>"
			+ "<modifyPrePolicyReturn>false</modifyPrePolicyReturn>"
			+ "</modifyPrePolicyResponse>");
		mockClient.sendRequest(RequestCreators.withPayload(requestPayload)).andExpect(ResponseMatchers.payload(responsePayload));
	}

}
