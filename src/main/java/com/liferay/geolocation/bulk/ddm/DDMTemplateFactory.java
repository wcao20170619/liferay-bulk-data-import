package com.liferay.geolocation.bulk.ddm;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DDMTemplateFactory.class)
public class DDMTemplateFactory {

	public DDMTemplate createTemplate(
		long userId, long groupId, long structureId, String resourceClassName)
		throws Exception {

		return addTemplate(
			userId, groupId, structureId, resourceClassName,
			TemplateConstants.LANG_TYPE_VM, getSampleTemplateXSL(),
			LocaleUtil.getSiteDefault());
	}

	private static String getSampleTemplateXSL() {
		return "$name.getData()";
	}

	private DDMTemplate addTemplate(
		long userId, long groupId, long structureId, String resourceClassName,
		String language, String script, Locale defaultLocale)
		throws Exception {

		return addTemplate(
			userId, groupId,
			classNameLocalService.getClassNameId(DDMStructure.class),
			structureId, resourceClassName, language, script, defaultLocale);
	}

	private DDMTemplate addTemplate(
		long userId, long groupId, long classNameId, long classPK,
		String resourceClassName, String language, String script,
		Locale defaultLocale)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(defaultLocale, "GeoSearch Template");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return ddmTemplateLocalService.addTemplate(
			userId, groupId, classNameId, classPK,
			classNameLocalService.getClassNameId(resourceClassName),
			nameMap, null,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, language, script,
			serviceContext);
	}

	@Reference
	protected DDMTemplateLocalService ddmTemplateLocalService;

	@Reference
	protected ClassNameLocalService classNameLocalService;

}