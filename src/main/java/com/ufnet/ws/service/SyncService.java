/*
 * @(#)SyncService.java $version 2016年5月20日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Sync Service.
 * @author cuizuoli
 * @date 2016年5月20日
 */
@Slf4j
@Service
public class SyncService {

	@Value("${ufnet.sync.uri}")
	private String syncUri;

	@Resource
	private WebServiceTemplate webServiceTemplate;

	/**
	 * sync
	 * @param request
	 * @return
	 */
	public Object sync(Object request) {
		Object response = null;
		if (StringUtils.isNotEmpty(syncUri)) {
			response = webServiceTemplate.marshalSendAndReceive(syncUri, request);
			log.debug(response.toString());
		}
		return response;
	}

}
