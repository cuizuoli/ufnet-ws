/*
 * @(#)DateType.java $version 2016年5月20日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.enumeration;

import java.util.regex.Pattern;

/**
 * The enumeration representing date type.
 * @author cuizuoli
 * @date 2016年5月20日
 */
public enum DateType {
	DATE("^\\d{4}-\\d{2}-\\d{2}$"),
	DATE_TIME("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
	private final String pattern;

	private DateType(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	/**
	 * instanceOf
	 * @param date
	 * @return
	 */
	public static DateType instanceOf(String date) {
		for (DateType dateType : DateType.values()) {
			if (Pattern.matches(dateType.getPattern(), date)) {
				return dateType;
			}
		}
		return null;
	}
}
