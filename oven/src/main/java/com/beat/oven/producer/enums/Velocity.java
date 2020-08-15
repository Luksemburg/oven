package com.beat.oven.producer.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Velocity {

	VELOCITY_PPP((byte)0x28),
	VELOCITY_PP((byte)0x30),
	VELOCITY_P((byte)0x38),
	VELOCITY_MP((byte)0x40),
	VELOCITY_MF((byte)0x48),
	VELOCITY_F((byte)0x50),
	VELOCITY_FF((byte)0x58),
	VELOCITY_FFF((byte)0x60);
	
	private byte value;
	Velocity(byte value){
		this.value = value;
	}
	
	public byte val() {
		return value;
	}
	
	private static final List<Velocity> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));	

	public static Velocity get(int index)  {
		return VALUES.get(index);
	}
}
