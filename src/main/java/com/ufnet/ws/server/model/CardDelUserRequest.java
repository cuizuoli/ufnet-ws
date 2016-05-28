/*
 * @(#)CardDelUserRequest.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ufnet.ws.SimpleConstants;

import lombok.ToString;

/**
 * ufnet-ws
 * com.ufnet.ws.server.model.CardDelUserRequest.java
 * @author st13902
 * @date 2013-5-16
 */
@ToString
@XmlRootElement(name = "CardDelUser", namespace = SimpleConstants.NAMESPACE)
public class CardDelUserRequest {
	private String userId;
	private String appName;

	@XmlElement(name = "userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name = "appName")
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
