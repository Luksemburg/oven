package com.beat.oven.producer.entity;

import java.io.Serializable;

public class Note implements Serializable {
	
	private static final long serialVersionUID = 352211145587L;
	
	public final static byte TAKE = (byte)0x90; 		// + channel number
	public final static byte RELEASE = (byte)0x80;		// + channel number
	public final static byte[] RELEASE_TAIL = {0x40, 0x00};
	
	private byte pitch;
	private byte velocity;
	private byte[] duration;
	
	public byte getPitch() {
		return this.pitch;
	}
	public void setPitch(byte pitch) {
		this.pitch = pitch;
	}
	public byte getVelocity() {
		return this.velocity;
	}
	public void setVelocity(byte velocity) {
		this.velocity = velocity;
	}
	public byte[] getDuration() {
		return this.duration;
	}
	public void setDuration(byte[] duration) {
		this.duration = duration;
	}
}
