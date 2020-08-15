package com.beat.oven.producer.entity;

import java.io.Serializable;

public class Chord implements Serializable {
	
	private static final long serialVersionUID = 100555684L;
	
	private Note[] notes;
	
	public Note[] getNotes() {
		return this.notes;
	}
	public void setNotes(Note[] notes) {
		this.notes = notes;
	}
	
	public void append(Note note) {
		Note[] temp = new Note[notes.length + 1];
		for(int i = 0; i < temp.length; i++) {
			if(i < notes.length) {
				temp[i] = notes[i];
			}else {
				temp[i] = note;
			}
		}
		notes = temp;
	}
}
