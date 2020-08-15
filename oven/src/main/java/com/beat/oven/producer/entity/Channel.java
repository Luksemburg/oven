package com.beat.oven.producer.entity;

import java.io.Serializable;

public class Channel implements Serializable {
	
	private static final long serialVersionUID = 2254562158L;
	
	private int number;
	private Chord[] chords;
	
	public Channel(int number){
		this.number = number;
	}		
	
	public Channel() {
		super();
	}
	
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Chord[] getChords() {
		return this.chords;
	}
	public void setChords(Chord[] chords) {
		this.chords = chords;
	}
	
	public void append(Chord chord) {
		Chord[] temp = new Chord[chords.length + 1];
		for(int i = 0; i < temp.length; i++) {
			if(i < chords.length) {
				temp[i] = chords[i];
			}else {
				temp[i] = chord;
			}
		}
		chords = temp;
	}
}
