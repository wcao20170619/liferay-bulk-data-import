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

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true, property = {
	"com.liferay.portlet.add-default-resource=true",
	"com.liferay.portlet.css-class-wrapper=" +
		"portlet-geolocation-bulk-load",
	"com.liferay.portlet.display-category=com.liferay.geolocation.bulk.category",
	"com.liferay.portlet.icon=/icons/search.png",
	"com.liferay.portlet.instanceable=true",
	"com.liferay.portlet.layout-cacheable=true",
	"com.liferay.portlet.preferences-owned-by-group=true",
	"com.liferay.portlet.private-request-attributes=false",
	"com.liferay.portlet.private-session-attributes=false",
	"com.liferay.portlet.restore-current-view=false",
	"com.liferay.portlet.use-default-template=true",
	"javax.portlet.display-name=" +
		"Geolocation Bulk Load",
	"javax.portlet.expiration-cache=0",
	"javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=" +
		"/geolocation/bulk/portlet/GeolocationBulkLoadPortlet_view.jsp",
	"javax.portlet.name=" + GeolocationBulkLoadPortletKeys.PORTLET_NAME,
	"javax.portlet.resource-bundle=content.Language",
	"javax.portlet.security-role-ref=guest,power-user,user",
	"javax.portlet.supports.mime-type=text/html"
}, service = {
	Portlet.class
})
public class GeolocationBulkLoadPortlet extends MVCPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		GeolocationBulkLoadPortletDisplayContext dc =
			new GeolocationBulkLoadPortletDisplayContext();

		dc.setDryRunParameterName("dryRun");
		dc.setLimitParameterName("limit");

		renderRequest.setAttribute(
			GeolocationBulkLoadPortletDisplayContext.class.getName(), dc);

		super.render(renderRequest, renderResponse);
	}

}