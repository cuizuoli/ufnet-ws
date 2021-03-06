/*
 * @(#)GetUserInfoResponse.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.ToString;

/**
 * ufnet-ws
 * com.ufnet.ws.server.model.GetUserInfoResponse.java
 * @author st13902
 * @date 2013-5-16
 */
@ToString
@XmlRootElement(name = "getUserInfoResponse")
public class GetUserInfoResponse {
	private String returnCode;

	@XmlElement(name = "getUserInfoReturn")
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
