package com.liferay.geolocation.bulk.util;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JournalArticleParser {
	
	JournalArticleDemoDataset parse() throws Exception {
		String name = "JournalArticleRequests.json";

		InputStream in = this.getClass().getResourceAsStream(name);

		ObjectMapper m = new ObjectMapper();

		JournalArticleRequest[] entries = m.readValue(in, JournalArticleRequest[].class);

		return new JournalArticleDemoDataset(entries);
	}

}
