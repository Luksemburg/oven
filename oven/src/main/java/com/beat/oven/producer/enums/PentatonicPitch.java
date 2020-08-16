package com.beat.oven.producer.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PentatonicPitch {	
	
	// --- for flute ONLY minor pentatonic
	
	C4((byte)0x3C),
	D4((byte)0x3E),
	F4((byte)0x41),
	G4((byte)0x43),	
	Ais4((byte)0x46),
	C5((byte)0x48),
	D5((byte)0x4A),
	F5((byte)0x4D),
	G5((byte)0x4F),	
	Ais5((byte)0x52),
	C6((byte)0x54),
	D6((byte)0x56),	
	F6((byte)0x59),
	G6((byte)0x5B),
	Ais6((byte)0x5E),
	C7((byte)0x60),
	
;	
	
	private byte value;
	PentatonicPitch(byte value){
		this.value = value;
	}
	
	public byte val() {
		return value;
	}	
	
	private static final List<PentatonicPitch> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));	

	public static PentatonicPitch get(int index)  {
		return VALUES.get(index);
	}
	
}
