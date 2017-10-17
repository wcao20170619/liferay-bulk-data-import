package com.liferay.geolocation.bulk.util;

import java.util.stream.Stream;

public class GeolocationDemoDataset {

	GeolocationDemoDataset(Request311[] entries) {
		this.entries = entries;
	}

	public Stream<Request311> entries() {
		return Stream.of(entries);
	}

	Request311[] entries;

}
