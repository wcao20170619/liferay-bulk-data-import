package com.liferay.geolocation.bulk.ddm;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DDMStructureFactory.class)
public class DDMStructureFactory {

	public DDMStructure createDDMStructure(
		long userId, long scopeGroupId, String className, DDMForm ddmForm,
		ServiceContext serviceContext)
		throws Exception {

		return addStructure(
			userId, scopeGroupId, className, 0, ddmForm,
			LocaleUtil.getSiteDefault(), serviceContext);
	}

	private DDMStructure addStructure(
		long userId, long groupId, String className, long parentStructureId,
		DDMForm ddmForm, Locale defaultLocale,
		ServiceContext serviceContext)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(defaultLocale, STRUCTURE_NAME);

		DDMFormLayout ddmFormLayout = ddm.getDefaultDDMFormLayout(ddmForm);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return ddmStructureLocalService.addStructure(
			userId, groupId, parentStructureId,
			classNameLocalService.getClassNameId(className),
			null, nameMap, null, ddmForm,
			ddmFormLayout, StorageType.JSON.toString(),
			DDMStructureConstants.TYPE_DEFAULT, serviceContext);
	}

	@Reference
	protected DDM ddm;

	@Reference
	protected DDMStructureLocalService ddmStructureLocalService;

	@Reference
	protected ClassNameLocalService classNameLocalService;

	private static final String STRUCTURE_NAME = "GeoSearch Structure";

}
