package com.beat.oven.network;

public interface IThinkable {
	void think();
	void forwardLearn();
	void reverseLearn();
	void saveWeights();
	void loadWeights();
	void reset();
}
