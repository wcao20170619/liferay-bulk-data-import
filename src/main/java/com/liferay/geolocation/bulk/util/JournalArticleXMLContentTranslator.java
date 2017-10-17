package com.liferay.geolocation.bulk.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.xml.Document;

public class JournalArticleXMLContentTranslator extends Request311ToJournalArticleXMLContentTranslator {
	
	public String translate(JournalArticleRequest r) {
		
		Document document = createEmptyDocument();
	
		String name =
				r.case_title + " | " +
				r.locale;

		addFieldSampleStructuredContent(
			document, "name", name);
		
		addFieldSampleStructuredContent(
			document, "case_title", r.case_title);

		addFieldSampleStructuredContent(
			document, "content", r.content);
		addFieldSampleStructuredContent(
			document, "locale", r.locale);
		addFieldSampleStructuredContent(
			document, "target_dt", r.target_dt);
		addFieldSampleStructuredContent(
			document, "source", r.source);

		return document.asXML();
	}

}
