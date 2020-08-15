package com.beat.oven.producer.enums;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Sys {
	//system
	TERMINATOR(new byte[] {0x00, (byte)0xFF}),
	TERMINATOR_CHANNEL(new byte[] {0x2F, 0x00}),
	
	TEMP_MARKER(new byte[] {0x51, 0x03}),
	TIME_SIGNATURE(new byte[] {0x58, 0x04}),		// marker + value size
	END(new byte[] {(byte)0xFF, 0x2F, 0x00}),
	
	;

	private byte[] value;
	Sys(byte[] value){
		this.value = value;
	}
	
	public byte[] val() {
		return value;
	}
	
	private static final List<Sys> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final SecureRandom RANDOM = new SecureRandom();

	public static Sys randomDuration()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
