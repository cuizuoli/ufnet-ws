/*
 * @(#)ResumeUserInfoResponse.java $version 2013-5-17
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
 * com.ufnet.ws.server.model.ResumeUserInfoResponse.java
 * @author st13902
 * @date 2013-5-17
 */
@ToString
@XmlRootElement(name = "ResumeUserInfoResponse")
public class ResumeUserInfoResponse {
	private int returnCode;

	@XmlElement(name = "ResumeUserInfoReturn")
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
}
