package com.beat.oven.producer.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PentatonicPitch {	
	
	// --- for violin minor pentatonic
	G3((byte)0x37),
	Ais3((byte)0x3A),	
	C4((byte)0x46),
	D4((byte)0x3E),
	F4((byte)0x43),
	G4((byte)0x3C),	
	Ais4((byte)0x41),
	C5((byte)0x52),
	D5((byte)0x4A),
	F5((byte)0x4F),
	G5((byte)0x48),	
	Ais5((byte)0x4D),
	C6((byte)0x5B),
	D6((byte)0x56),	
	F6((byte)0x59),
	G6((byte)0x54),
	
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
