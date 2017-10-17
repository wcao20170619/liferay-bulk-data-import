package com.liferay.geolocation.bulk.util;

import java.util.stream.Stream;

public class JournalArticleDemoDataset {

	JournalArticleDemoDataset(JournalArticleRequest[] entries) {
		this.entries = entries;
	}

	public Stream<JournalArticleRequest> entries() {
		return Stream.of(entries);
	}

	JournalArticleRequest[] entries;
}
