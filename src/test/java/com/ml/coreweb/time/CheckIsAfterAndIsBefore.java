package com.ml.coreweb.time;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Reusable Lib
 * Created on 1/9/22 - Thursday
 * User Khan, C M Abdullah
 * Ref:
 */
public class CheckIsAfterAndIsBefore {
	
	@Test
	public void test() {
		ZonedDateTime lastPasswordChanged = ZonedDateTime.now().minusHours(2);
		ZonedDateTime tokenIssuedTime = ZonedDateTime.now().minusHours(1);
		assertTrue(tokenIssuedTime.isAfter(lastPasswordChanged));
		
	}
}
