/**
 * Copyright (c) 2011-2013, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jfinal.sog.kit.date;

import java.util.Date;
import com.jfinal.kit.StringKit;

/**
 * DateKit.
 */
public class DateKit {
	
	public static String dateFormat = DateProvider.YYYY_MM_DD;
	public static String timeFormat = DateProvider.YYYY_MM_DD_HH_MM_SS;
	
	public static void setDateFromat(String dateFormat) {
		if (StringKit.isBlank(dateFormat))
			throw new IllegalArgumentException("dateFormat can not be blank.");
		DateKit.dateFormat = dateFormat;
	}
	
	public static void setTimeFromat(String timeFormat) {
		if (StringKit.isBlank(timeFormat))
			throw new IllegalArgumentException("timeFormat can not be blank.");
		DateKit.timeFormat = timeFormat;
	}
	
	public static Date toDate(String dateStr) {
		throw new RuntimeException("Not finish!!!");
	}
	
	public static String toStr(Date date) {
		return toStr(date, DateKit.dateFormat);
	}
	
	public static String toStr(Date date, String format) {
		throw new RuntimeException("Not finish!!!");
	}
}
