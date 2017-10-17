/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.geolocation.bulk.portlet;

/**
 * @author André de Oliveira
 */
public class GeolocationBulkLoadPortletDisplayContext {

	public String getDryRunParameterName() {
		return _dryRunParameterName;
	}

	public String getLimitParameterName() {
		return _limitParameterName;
	}

	public String getLimitString() {
		return _limitString;
	}

	public boolean isDryRun() {
		return _dryRun;
	}

	public void setDryRun(boolean dryRun) {
		_dryRun = dryRun;
	}

	public void setDryRunParameterName(String dryRunParameterName) {
		_dryRunParameterName = dryRunParameterName;
	}

	public void setLimitParameterName(String limitParameterName) {
		_limitParameterName = limitParameterName;
	}

	public void setLimitString(String limitString) {
		_limitString = limitString;
	}

	private boolean _dryRun;
	private String _dryRunParameterName;
	private String _limitParameterName;
	private String _limitString;

}