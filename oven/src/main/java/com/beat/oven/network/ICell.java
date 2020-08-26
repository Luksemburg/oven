package com.beat.oven.network;

public interface ICell {	
	void setOldState(float[] oldState);
	void setOldOutput(float[] oldOutput);
	
	void input(float [] data);
	void work();
	float[] output();
	float[] state();
}
