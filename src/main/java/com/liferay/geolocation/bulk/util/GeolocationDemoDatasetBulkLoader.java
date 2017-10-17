package com.liferay.geolocation.bulk.util;

public class GeolocationDemoDatasetBulkLoader {

	public GeolocationDemoDatasetBulkLoader(
		JournalArticleBulkLoader journalArticleBulkLoader) {

		this.journalArticleBulkLoader = journalArticleBulkLoader;
	}

	public void load(int limit, boolean dryRun) throws Exception {
		GeolocationDemoDataset dataset = new Parser_311_requests_json().parse();

		journalArticleBulkLoader.setDataset(dataset);
		journalArticleBulkLoader.setLimit(limit);
		journalArticleBulkLoader.setDryRun(dryRun);

		journalArticleBulkLoader.load();
	}
	
	public void loadJArticle(int limit, boolean dryRun) throws Exception {
		JournalArticleDemoDataset dataset = new JournalArticleParser().parse();

		journalArticleBulkLoader.setJaDataset(dataset);
		journalArticleBulkLoader.setLimit(limit);
		journalArticleBulkLoader.setDryRun(dryRun);

		journalArticleBulkLoader.loadJArticle();
	}

	private final JournalArticleBulkLoader journalArticleBulkLoader;

}
