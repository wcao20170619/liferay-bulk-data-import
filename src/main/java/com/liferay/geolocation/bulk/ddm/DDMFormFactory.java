package com.liferay.geolocation.bulk.ddm;

import static com.liferay.dynamic.data.mapping.model.DDMFormFieldType.TEXT;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Locale;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

@Component(service = DDMFormFactory.class)
public class DDMFormFactory {

	public DDMForm createDDMForm() {
		String keyword_indexType = "keyword";
		String text_indexType = "text";

		boolean repeatable = false;

		DDMForm ddmForm = getSampleDDMForm(
			"name", "string", text_indexType, repeatable, TEXT,
			new Locale[] {
				LocaleUtil.US
			}, LocaleUtil.US);

		addFieldToForm(
			ddmForm, "geolocation", "string", DDMFormFieldType.GEOLOCATION,
			keyword_indexType);
		addFieldToForm(
			ddmForm, "case_enquiry_id", "string", TEXT, keyword_indexType); // 101000295623
		addFieldToForm(ddmForm, "case_title", "string", TEXT, text_indexType); // Improper
																				// Storage
																				// of
																				// Trash
																				// (Barrels)
		addFieldToForm(
			ddmForm, "city_council_district", "string", TEXT,
			keyword_indexType); // 1
		addFieldToForm(
			ddmForm, "closed_dt", "datetime", "datetime", keyword_indexType); // 2011-07-06T16:55:32
		addFieldToForm(
			ddmForm, "closure_reason", "string", TEXT, text_indexType); // Case
																		// Closed
																		// VIOISS:
																		// Violation
																		// Filed
		addFieldToForm(
			ddmForm, "department", "string", TEXT, keyword_indexType); // ISD
		addFieldToForm(
			ddmForm, "fire_district", "string", TEXT, keyword_indexType); // 1
		addFieldToForm(ddmForm, "location", "string", TEXT, text_indexType); // 347
																				// Meridian
																				// St
																				// East
																				// Boston
																				// MA
																				// 02128
		addFieldToForm(
			ddmForm, "location_street_name", "string", TEXT, text_indexType); // 347
																				// Meridian
																				// St
		addFieldToForm(ddmForm, "neighborhood", "string", TEXT, text_indexType); // East
																					// Boston
		addFieldToForm(
			ddmForm, "ontime_status", "string", TEXT, keyword_indexType); // ONTIME
		addFieldToForm(
			ddmForm, "open_dt", "datetime", "datetime", keyword_indexType); // 2011-07-05T14:48:09
		addFieldToForm(
			ddmForm, "police_district", "string", TEXT, keyword_indexType); // a7
		addFieldToForm(
			ddmForm, "property_id", "string", TEXT, keyword_indexType); // 84847
		addFieldToForm(
			ddmForm, "pwd_district", "string", TEXT, keyword_indexType); // 09
		addFieldToForm(ddmForm, "queue", "string", TEXT, text_indexType); // ISD_Code
																			// Enforcement
																			// (INTERNAL)
		addFieldToForm(ddmForm, "reason", "string", TEXT, text_indexType); // Code
																			// Enforcement
		addFieldToForm(ddmForm, "subject", "string", TEXT, text_indexType); // Inspectional
																			// Services
		addFieldToForm(
			ddmForm, "target_dt", "datetime", "datetime", keyword_indexType); // 2011-07-07T14:46:15
		addFieldToForm(ddmForm, "type", "string", TEXT, text_indexType); // Improper
																			// Storage
																			// of
																			// Trash
																			// (Barrels)

		return ddmForm;
	}

	private static DDMForm getSampleDDMForm(
		String name, String dataType, String indexType, boolean repeatable,
		String type, Locale[] availableLocales, Locale defaultLocale) {

		DDMForm ddmForm = new DDMForm();

		Set<Locale> availableLocalesSet = SetUtil.fromArray(availableLocales);

		ddmForm.setAvailableLocales(availableLocalesSet);
		ddmForm.setDefaultLocale(defaultLocale);

		DDMFormField ddmFormField = new DDMFormField(name, type);

		ddmFormField.setDataType(dataType);
		ddmFormField.setIndexType(indexType);
		ddmFormField.setLocalizable(true);
		ddmFormField.setRepeatable(repeatable);

		LocalizedValue label = new LocalizedValue(defaultLocale);

		label.addString(
			defaultLocale, "Field_" + LocaleUtil.toLanguageId(defaultLocale));

		for (Locale locale : availableLocalesSet) {
			label.addString(locale, "Field_" + LocaleUtil.toLanguageId(locale));
		}

		ddmFormField.setLabel(label);

		ddmForm.addDDMFormField(ddmFormField);

		return ddmForm;
	}

	private void addFieldToForm(
		DDMForm ddmForm, String name, String dataType, String type,
		String indexType) {

		DDMFormField ddmFormField = new DDMFormField(name, type);

		ddmFormField.setDataType(dataType);
		ddmFormField.setIndexType(indexType);
		ddmFormField.setLocalizable(true);
		ddmFormField.setRepeatable(false);

		LocalizedValue label = new LocalizedValue(defaultLocale);

		label.addString(
			defaultLocale, "Field_" + LocaleUtil.toLanguageId(defaultLocale));

		for (Locale locale : _availableLocalesSet) {
			label.addString(locale, "Field_" + LocaleUtil.toLanguageId(locale));
		}

		ddmFormField.setLabel(label);

		ddmForm.addDDMFormField(ddmFormField);
	}

	private final static Locale defaultLocale = LocaleUtil.US;

	private final Locale[] _availableLocales = new Locale[] {
		LocaleUtil.US
	};

	private final Set<Locale> _availableLocalesSet =
		SetUtil.fromArray(_availableLocales);

}