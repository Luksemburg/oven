package com.beat.oven.producer;

import java.io.FileOutputStream;

import com.beat.oven.producer.entity.Channel;
import com.beat.oven.producer.enums.Instrument;
import com.beat.oven.producer.enums.Sys;

public class ProduceMidi implements IProducerable {
	
	private final static byte[] MIDI_HEADER = {0x4D, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06}; 	//"MThd" + 00 00 00 06;	
	private final static byte[] FORMAT = {0x00, 0x01};													//midi format 0 - 1 channel totally || 1 - many channels
	private  byte[] numOfChannels = {0x00, 0x02};													//how many midi channels
	private  byte[] ticsInBeat = {0x01, (byte) 0xE0};											// 480 tics in 1/4
	
	private final static byte[] CHANNEL_HEADER = {0x4D, 0x54, 0x72, 0x6B}; 					//"MTrk"
	private final static byte[] META_CHANNEL_LENGTH = {0x00, 0x00, 0x00, 0x0B};						//length of midi meta channel	
																							//"MTrk"
	private  byte[] temp = {(byte)0x07, (byte)0xA1, 0x20};							//120 bpm
	private  byte[] channelLength = {0x00, 0x00, 0x00, 0x00};							//length of midi block
	
	private  byte[] timeSignatureValue = {0x04, 0x02, 0x18, 0x08, 0x00};					//4/4 + 0x00
	
	private byte[] data;		
	
	
	//MIDI_HEADER + FORMAT + numOfChannels + ticsInBeat + CHANNEL_HEADER + META_CHANNEL_LENGTH + Sys.TERMINATOR + Sys.TEMP_MARKER + 
	//temp + Sys.TERMINATOR + Sys.TERMINATOR_CHANNEL + CHANNEL_HEADER + Sys.TERMINATOR + Instrument.FLUTE + Sys.TERMINATOR + 
	//Sys.TIME_SIGNATURE + timeSignatureValue + === NOTES === + Sys.END 
	
	//=== NOTES ==
	//for each note
	//Note.TAKE + channel.getNumber() + note.getPitch() +  note.getVelocity() + note.getDuration()[0] + note.getDuration()[1] + 
	//Note.RELEASE + channel.getNumber() + note.getPitch() + Note.RELEASE_TAIL
	
	

	@Override
	public void write(Channel channel) {		// TODO: array	of channels	
		
		pushToData(MIDI_HEADER);
		pushToData(FORMAT);
		pushToData(numOfChannels);
		pushToData(ticsInBeat);
		
		pushToData(CHANNEL_HEADER);
		pushToData(META_CHANNEL_LENGTH);
		pushToData(Sys.TERMINATOR.val());
		pushToData(Sys.TEMP_MARKER.val());
		pushToData(temp);
		pushToData(Sys.TERMINATOR.val());
		pushToData(Sys.TERMINATOR_CHANNEL.val());
		
		pushToData(CHANNEL_HEADER);
		pushToData(Sys.TERMINATOR.val());
		pushToData(Instrument.FLUTE.val());
		pushToData(Sys.TERMINATOR.val());
		pushToData(Sys.TIME_SIGNATURE.val());
		pushToData(timeSignatureValue);
		
		
		
		pushToData(Sys.END.val());
		
		
		try (FileOutputStream fos = new FileOutputStream("D:\\FOO_CODE\\EclipseWorkSpace\\oven\\output.mid")) {		// TODO: move hard link to config	
		   fos.write(data);			   
		}catch(Exception e){
			e.printStackTrace();
		}			
	}	
	
	private void pushToData(byte[] payload) {
		byte[] result = new byte[data.length + payload.length];
		
		for(int i = 0; i < data.length; i++){
			result[i] = data[i];
		}
		
		for(int i = 0; i < payload.length; i++){
			result[data.length + i] = payload[i];
		}
		
		data = result;
	}

}
