package com.beat.oven.network;

public interface ICell {	
	void setOldCondition(float[] oldCondition);
	void setOldOutput(float[] oldOutput);
	void input(float [] data);
	void work();
	float[] output();
	float[] condition();
}
