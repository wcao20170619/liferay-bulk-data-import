/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.java.lang;

import com.liferay.portal.kernel.util.Validator;

import java.util.Optional;

/**
 * @author André de Oliveira
 */
public class StringUtil {

	public static Optional<String> maybe(String s) {
		s = com.liferay.portal.kernel.util.StringUtil.trim(s);

		if (Validator.isBlank(s)) {
			return Optional.empty();
		}

		return Optional.of(s);
	}

	public static Optional<String[]> maybe(String[] texts) {
		if (texts == null) {
			return Optional.empty();
		}

		if (texts.length == 0) {
			return Optional.empty();
		}

		return Optional.of(texts);
	}

}