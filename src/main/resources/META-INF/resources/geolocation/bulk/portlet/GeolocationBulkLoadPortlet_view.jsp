<%--
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
--%>

<%@page import="com.liferay.geolocation.bulk.portlet.GeolocationBulkLoadPortletDisplayContext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>

<portlet:defineObjects />

<%
GeolocationBulkLoadPortletDisplayContext geolocationBulkLoadPortletDisplayContext = (GeolocationBulkLoadPortletDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(GeolocationBulkLoadPortletDisplayContext.class.getName()));
%>

<portlet:actionURL name="<%= com.liferay.geolocation.bulk.portlet.GeolocationBulkLoadPortletKeys.MVC_COMMAND_NAME %>" var="portletURL">
	<portlet:param name="mvcActionCommandName" value="redirectSearchBar" />
</portlet:actionURL>

<aui:form action="<%= portletURL %>" method="post" name="fm">
	<aui:fieldset id="searchContainer">
		<div class="input-group search-bar">
			<aui:field-wrapper cssClass="search-field" inlineField="<%= true %>">
				<aui:input
					autoFocus="true"
					cssClass="search-bar-input"
					label="com.liferay.geolocation.bulk.how-many"
					name="<%= geolocationBulkLoadPortletDisplayContext.getLimitParameterName() %>"
					placeholder="com.liferay.geolocation.bulk.how-many"
					title="search"
					type="text"
					value="<%= geolocationBulkLoadPortletDisplayContext.getLimitString() %>"
				/>
			</aui:field-wrapper>

			<aui:input label="com.liferay.geolocation.bulk.dry-run" name="<%= geolocationBulkLoadPortletDisplayContext.getDryRunParameterName() %>" type="checkbox" value="<%= geolocationBulkLoadPortletDisplayContext.isDryRun() %>" />

			<aui:field-wrapper cssClass="input-group-btn search-field" inlineField="<%= true %>">
				<aui:button icon="icon-search" primary="<%= false %>" type="submit" value="" />
			</aui:field-wrapper>
		</div>
	</aui:fieldset>
</aui:form>