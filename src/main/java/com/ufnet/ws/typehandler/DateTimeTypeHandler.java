/*
 * @(#)DateTimeTypeHandler.java $version 2015年8月28日
 *
 * Copyright 2015 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

/**
 * MyBatis type handler for DateTime.
 * @author cuizuoli
 * @date 2015年8月28日
 */
@MappedTypes(DateTime.class)
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {

	/**
	 * Convert timestamp to DateTime in DB with column name.<br>
	 * if timestamp is null, then return null.
	 */
	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		DateTime dateTime = null;
		Timestamp timestamp = rs.getTimestamp(columnName);
		if (timestamp != null) {
			dateTime = new DateTime(timestamp);
		}
		return dateTime;
	}

	/**
	 * Convert timestamp to DateTime in DB with column index.<br>
	 * if timestamp is null, then return null.
	 */
	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		DateTime dateTime = null;
		Timestamp timestamp = rs.getTimestamp(columnIndex);
		if (timestamp != null) {
			dateTime = new DateTime(timestamp);
		}
		return dateTime;
	}

	/**
	 * Convert timestamp to DateTime in DB with column index.<br>
	 * if timestamp is null, then return null.
	 */
	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		DateTime dateTime = null;
		Timestamp timestamp = cs.getTimestamp(columnIndex);
		if (timestamp != null) {
			dateTime = new DateTime(timestamp);
		}
		return dateTime;
	}

	/**
	 * Convert DateTime to timestamp when save to database.
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
	}

}
