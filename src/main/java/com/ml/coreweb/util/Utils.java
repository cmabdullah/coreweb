package com.ml.coreweb.util;

import com.datastax.driver.core.utils.UUIDs;

/**
 * BookWormV2
 * Created on 19/8/22 - Friday
 * User Khan, C M Abdullah
 * Ref:
 */
public class Utils {
	public static String getUniqueUUID(){
		return  UUIDs.timeBased().toString();
	}
}
