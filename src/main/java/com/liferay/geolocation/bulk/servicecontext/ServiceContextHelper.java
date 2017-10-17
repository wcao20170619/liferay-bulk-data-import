
package com.liferay.geolocation.bulk.servicecontext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ServiceContextHelper.class)
public class ServiceContextHelper {

	public void init() throws Exception {
		_company = companyLocalService.getCompanyByWebId(COMPANY_WEB_ID);

		_user = getAdminUser(_company.getCompanyId());

		initUser(_user);

		_group = addGroup();

		_serviceContext = getServiceContext(_group.getGroupId());
	}

	public long getUserId() {
		return _user.getUserId();
	}

	public long getScopeGroupId() {
		return _serviceContext.getScopeGroupId();
	}

	public ServiceContext getServiceContext(long groupId)
		throws PortalException {

		Group group = groupLocalService.getGroup(groupId);

		User user = getAdminUser(group.getCompanyId());

		return getServiceContext(group, user.getUserId());
	}

	public ServiceContext getServiceContextGuest() throws PortalException {
		Group groupGuest = groupLocalService.getGroup(
			_company.getCompanyId(), GroupConstants.GUEST);

		return getServiceContext(
			_company.getCompanyId(), groupGuest.getGroupId(),
			_user.getUserId());
	}

	public ServiceContext getServiceContextScopeGroup() throws PortalException {
		return getServiceContext(_serviceContext.getScopeGroupId());
	}

	private Group addGroup() throws Exception {
		return addGroup(GroupConstants.DEFAULT_PARENT_GROUP_ID);
	}

	private Group addGroup(long parentGroupId) throws Exception {
		return addGroup(
			_company.getCompanyId(), _user.getUserId(),
			parentGroupId);
	}

	private Group addGroup(
		long companyId, long userId, long parentGroupId)
		throws Exception {

		String name = GROUP_NAME;
		String description = GROUP_DESCRIPTION;

		Group group = groupLocalService.fetchGroup(companyId, name);

		if (group != null) {
			return group;
		}

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), name);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(LocaleUtil.getDefault(), description);

		int type = GroupConstants.TYPE_SITE_OPEN;
		String friendlyURL =
			StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(name);
		boolean site = true;
		boolean active = true;
		boolean manualMembership = true;
		int membershipRestriction =
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION;

		return groupLocalService.addGroup(
			userId, parentGroupId, null, 0,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, descriptionMap, type,
			manualMembership, membershipRestriction, friendlyURL, site, active,
			getServiceContextGuest());
	}

	private static ServiceContext getServiceContext(Group group, long userId) {
		return getServiceContext(
			group.getCompanyId(), group.getGroupId(), userId);
	}

	private static ServiceContext getServiceContext(
		long companyId, long groupId, long userId) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		return serviceContext;
	}

	private User getAdminUser(long companyId) throws PortalException {
		Role role = roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = userLocalService.getRoleUsers(
			role.getRoleId(), 0, 1);

		if (!users.isEmpty()) {
			return users.get(0);
		}

		return null;
	}

	private static void initUser(User user) throws Exception {
		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);
	}

	@Reference
	protected CompanyLocalService companyLocalService;

	@Reference
	protected GroupLocalService groupLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

	@Reference
	protected UserLocalService userLocalService;

	private static final String COMPANY_WEB_ID = "liferay.com";

	private static final String GROUP_NAME = "GeoSearch Demo Site";

	private static final String GROUP_DESCRIPTION = "GeoSearch Demo Site";

	private Company _company;
	private Group _group;
	private User _user;

	private ServiceContext _serviceContext;

}