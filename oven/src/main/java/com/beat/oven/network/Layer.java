package com.beat.oven.network;

public class Layer {
	ICell[] cells;
	
	Layer(int size){
		super();
		cells = new LSTM_Cell[size];
	}
	
	public float[][] work(float [][] input) {
		float[][] result = new float[input.length][];
		
		//TODO operate cells
		
		
		return result;
	}
}
