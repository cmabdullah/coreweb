package com.ml.coreweb.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
public class DateTimeUtil {
	public static final int OFF_SET_BD = 6;
	public static final ZoneOffset ZONE_OFFSET_BD = ZoneOffset.ofHours(OFF_SET_BD);
	
	public static ZonedDateTime timeNow() {
		return ZonedDateTime.now(ZONE_OFFSET_BD);
	}
	
	public static long timeNowToEpochSecond() {
		return timeNow().toInstant().toEpochMilli();
	}
	
	public static Date dateFromZonedDateTimeToNow() {
		Instant instant = timeNow().toInstant();
		// Create Date instance out of Instant
		return Date.from(instant);
	}
	
	public static Date dateFromZonedDateTimeToNow(ZonedDateTime zonedDateTime) {
		// We just say what Instant is and how it used in the blog
		Instant instant = zonedDateTime.toInstant();
		// Create Date instance out of Instant
		return Date.from(instant);
	}
	
	public static ZonedDateTime zonedDateTimeFromInstant(Instant instant) {
		return instant.atZone(ZONE_OFFSET_BD.normalized());
	}
	
	public static ZonedDateTime zonedDateTimeFromDate(Date date) {
		return zonedDateTimeFromInstant(date.toInstant());
	}
}
