package com.beat.oven.producer;

public enum ByteCommands {
	
	DURATION_1(new byte[]{(byte)0x8F}),
	DURATION_2(new byte[]{(byte)0x87, 0x40}),
	DURATION_4(new byte[]{(byte)0x83, 0x60}),
	DURATION_8(new byte[]{(byte)0x81, 0x70}),
	DURATION_16(new byte[]{0x78}),
	DURATION_32(new byte[]{0x3C}),
	DURATION_64(new byte[]{0x1E}),
	
	DYNAMIC_PPP(new byte[]{0x28}),
	DYNAMIC_PP(new byte[]{0x30}),
	DYNAMIC_P(new byte[]{0x38}),
	DYNAMIC_MP(new byte[]{0x40}),
	DYNAMIC_MF(new byte[]{0x48}),
	DYNAMIC_F(new byte[]{0x50}),
	DYNAMIC_FF(new byte[]{0x58}),
	DYNAMIC_FFF(new byte[]{0x60}),
	
	//INSTRUMENTS
	
	NOTE_SNARE_HIT(new byte[]{0x26}),
	NOTE_SNARE_SIDE(new byte[]{0x25}),
	NOTE_SNARE_RIM(new byte[]{0x5B}),
	
	NOTE_HIHAT_CLOSED(new byte[]{0x2A}),
	NOTE_HIHAT_HALF(new byte[]{0x5C}),
	NOTE_HIHAT_OPEN(new byte[]{0x2E}),
	NOTE_HIHAT_PEDAL(new byte[]{0x2C}),
	
	KICK(new byte[]{0x23}),
	KICK_2(new byte[]{0x24}),
	
	TOM_HIGH_FLOOR(new byte[]{0x32}),
	TOM_HIGH(new byte[]{0x30}),
	TOM_MID(new byte[]{0x2F}),
	TOM_LOW(new byte[]{0x2D}),
	TOM_VERY_LOW(new byte[]{0x2B}),
	
	RIDE_EDGE(new byte[]{0x5D}),
	RIDE_MID(new byte[]{0x33}),
	RIDE_BELL(new byte[]{0x35}),
	
	SPLASH(new byte[]{0x37}),
	CHINA(new byte[]{0x34}),
	CRASH_HIT(new byte[]{0x31}),
	CRASH_MID(new byte[]{0x39}),
	COWBELL(new byte[]{0x38}),
;	
	
	private byte[] value;
	ByteCommands(byte[] value){
		this.value = value;
	}
	
	public byte[] val() {
		return value;
	}
}
