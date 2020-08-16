package com.beat.oven.producer.enums;

public enum Instrument {
	
	// 
	FLUTE(new byte[] {0x03, 0x05, (byte)0x46, (byte)0x6C, (byte)0x75, (byte)0x74, (byte)0x65, (byte)0x00, (byte)0xC0, (byte)0x49}),
	PIANO(new byte[] {0x03, 0x0E, (byte)0x41, (byte)0x63, (byte)0x6F,(byte)0x75, (byte)0x73, (byte)0x74, (byte)0x69, (byte)0x63, (byte)0x20, 
			(byte)0x50, (byte)0x69, (byte)0x61, (byte)0x6E, (byte)0x6F, (byte)0x00,(byte)0xC0, (byte)0x00}),
	VIOLIN(new byte[] {0x03, 0x06, (byte)0x56, (byte)0x69, (byte)0x6F, (byte)0x6C, (byte)0x69, (byte)0x6E, 0x00, (byte)0xC0, 0x28}),
	DRUMS(new byte[] {0x03, 0x07, 0x44, 0x72, 0x75, 0x6D, 0x6B, 0x69, 0x74, 0x00, (byte)0xC9, 0x00}),
		
	;
	
	private byte[] value;
	Instrument(byte[] value){
		this.value = value;
	}
	
	public byte[] val() {
		return value;
	}
}
