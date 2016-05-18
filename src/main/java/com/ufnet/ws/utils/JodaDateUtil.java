/*
 * @(#)JodaDateUtil.java $version 2013-5-16
 *
 * Copyright 2013 NHN ST. All rights Reserved.
 * NHN ST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * ufnet-ws
 * com.ufnet.ws.utils.JodaDateUtil.java
 * @author st13902
 * @date 2013-5-16
 */
public class JodaDateUtil {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	public static DateTime parseDate(String date) {
		return DATE_FORMATTER.parseDateTime(date);
	}

	public static DateTime parseDateTime(String datetime) {
		return DATETIME_FORMATTER.parseDateTime(datetime);
	}

	public static String formatDate(DateTime datetime) {
		return datetime.toString(DATE_FORMATTER);
	}
}
