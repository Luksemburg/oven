package com.beat.oven.producer.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Duration {
	
	//prof music
	
	DURATION_1(new byte[]{(byte)0x8F, 0x00}),
	DURATION_2(new byte[]{(byte)0x87, 0x40}),
	DURATION_4(new byte[]{(byte)0x83, 0x60}),
	DURATION_8(new byte[]{(byte)0x81, 0x70}),
	DURATION_16(new byte[]{0x78}),
	DURATION_32(new byte[]{0x3C}),
	DURATION_64(new byte[]{0x1E});
	
	private byte[] value;
	Duration(byte[] value){
		this.value = value;
	}
	
	public byte[] val() {
		return value;
	}
	
	private static final List<Duration> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));	

	public static Duration get(int index)  {
		return VALUES.get(index);
	}
}
