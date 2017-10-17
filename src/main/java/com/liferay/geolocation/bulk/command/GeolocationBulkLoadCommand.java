package com.liferay.geolocation.bulk.command;

import com.liferay.geolocation.bulk.util.GeolocationDemoDatasetBulkLoader;
import com.liferay.geolocation.bulk.util.JournalArticleBulkLoader;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
				immediate = true, service = GeolocationBulkLoadCommand.class,
	property = {
		"osgi.command.function=load",
		"osgi.command.function=loadJArticle",
		"osgi.command.scope=geolocation"
	}
)
public class GeolocationBulkLoadCommand {

	public void load(String limit) throws Exception {
		boolean dryRun = false;
		new GeolocationDemoDatasetBulkLoader(journalArticleBulkLoader).load(
			Integer.valueOf(limit), dryRun);
	}

	public void load() throws Exception {
		load("10");
	}
	
	public void loadJArticle() throws Exception {
		new GeolocationDemoDatasetBulkLoader(journalArticleBulkLoader).loadJArticle(
			Integer.valueOf(10), false);
	}

	@Activate
	protected void start() {
		System.out.println("Geolocation Bulk Load is ready......");
	}

	@Reference
	protected JournalArticleBulkLoader journalArticleBulkLoader;

}