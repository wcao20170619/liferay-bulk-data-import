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

import com.liferay.javax.portlet.PortletPreferencesHelper;

import java.util.Optional;

import javax.portlet.PortletPreferences;

/**
 * @author André de Oliveira
 */
public class GeolocationBulkLoadPortletPreferencesImpl
	implements GeolocationBulkLoadPortletPreferences {

	public GeolocationBulkLoadPortletPreferencesImpl(
		Optional<PortletPreferences> portletPreferencesOptional) {

		_portletPreferencesHelper = new PortletPreferencesHelper(
			portletPreferencesOptional);
	}

	@Override
	public boolean isDryRun() {
		return _portletPreferencesHelper.getBoolean(
			GeolocationBulkLoadPortletPreferences.PREFERENCE_KEY_DRY_RUN,
			false);
	}

	private final PortletPreferencesHelper _portletPreferencesHelper;

}