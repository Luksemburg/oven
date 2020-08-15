package com.beat.oven.producer.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum DrumPitch {

	// drum's notes
	
	NOTE_SNARE_HIT((byte)0x26),
	NOTE_SNARE_SIDE((byte)0x25),
	NOTE_SNARE_RIM((byte)0x5B),
	
	KICK((byte)0x23),
	KICK_2((byte)0x24),
	
	NOTE_HIHAT_CLOSED((byte)0x2A),
	NOTE_HIHAT_HALF((byte)0x5C),
	NOTE_HIHAT_OPEN((byte)0x2E),
	NOTE_HIHAT_PEDAL((byte)0x2C),		
	
	TOM_HIGH_FLOOR((byte)0x32),
	TOM_HIGH((byte)0x30),
	TOM_MID((byte)0x2F),
	TOM_LOW((byte)0x2D),
	TOM_VERY_LOW((byte)0x2B),
	
	RIDE_EDGE((byte)0x5D),
	RIDE_MID((byte)0x33),
	RIDE_BELL((byte)0x35),
	
	SPLASH((byte)0x37),
	CHINA((byte)0x34),
	CRASH_HIT((byte)0x31),
	CRASH_MID((byte)0x39),
	COWBELL((byte)0x38);
	
	private byte value;
	DrumPitch(byte value){
		this.value = value;
	}
	
	public byte val() {
		return value;
	}
	
	private static final List<DrumPitch> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));	

	public static DrumPitch get(int index)  {
		return VALUES.get(index);
	}
	
}
