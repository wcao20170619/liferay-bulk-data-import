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

import com.liferay.geolocation.bulk.util.GeolocationDemoDatasetBulkLoader;
import com.liferay.geolocation.bulk.util.JournalArticleBulkLoader;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(
	property = {
		"javax.portlet.name=" +
			GeolocationBulkLoadPortletKeys.PORTLET_NAME,
		"mvc.command.name=" +
			GeolocationBulkLoadPortletKeys.MVC_COMMAND_NAME
	},
	service = MVCActionCommand.class
)
public class GeolocationBulkLoadMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int limit = ParamUtil.getInteger(actionRequest, "limit", 10);

		boolean dryRun = ParamUtil.getBoolean(actionRequest, "dryRun", true);

		new GeolocationDemoDatasetBulkLoader(journalArticleBulkLoader).load(
			Integer.valueOf(limit), dryRun);
	}

	@Reference
	protected Portal portal;

	@Reference
	protected JournalArticleBulkLoader journalArticleBulkLoader;

}