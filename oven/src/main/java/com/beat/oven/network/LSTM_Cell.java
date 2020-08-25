package com.beat.oven.network;

import java.util.Random;

public class LSTM_Cell implements ICell {
	
	private float minWeightInit = 0.0f;
	private float maxWeightInit = 2.0f;
	
	private int vector_size;
	
	private float oldCondition[];		// C t-1
	private float oldOutput[];			// h t-1
	
	private float input[];			// X
	private float condition[];		// C
	private float output[];			// h
	
	private float forgetW[];		// sigma 1st layer weight for input vector
	private float forgetU[];		// sigma 1st layer weight for memory h_t-1 previous cell
	
	private float inW[];			// sigma 2st layer weight for input vector
	private float inU[];			// sigma 2st layer weight for memory h_t-1 previous cell
	
	private float tanhW[];			//tanh layer weight for input vector
	private float tanhU[];			//tanh layer weight for memory h_t-1 previous cell
	
	private float outW[];			// sigma 4st layer weight for input vector
	private float outU[];			// sigma 4st layer weight for memory h_t-1 previous cell	
	
	
	private float forgetBias[];			// f_t
	private float inputBias[];			// i_t
	private float tanhBias[];			// C_t
	private float outBias[];			// o_t
	
	private float forgetLearnRate[];			// f_t
	private float inputLearnRate[];				// i_t
	private float tanhLearnRate[];				// C_t
	private float outLearnRate[];				// o_t
	
	private float forgetMoment[];			// f_t
	private float inputMoment[];			// i_t
	private float tanhMoment[];				// C_t
	private float outMoment[];				// o_t
	
	
	public LSTM_Cell(int size) {
		super();
		this.vector_size = size;
		
		Random random = new Random();
		
		input = new float[size];
		output = new float[size];
		condition = new float[size];
		
		forgetW = new float[size];
		forgetU = new float[size];
		
		inW = new float[size];
		inU = new float[size];
		
		tanhW = new float[size];
		tanhU = new float[size];
		
		outW = new float[size];
		outU = new float[size];					
		
		forgetBias = new float[size];
		inputBias = new float[size];
		tanhBias = new float[size];		
		outBias = new float[size];
		
		//Init
		for(int i = 0; i < size; i++) {
			forgetW[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			forgetU[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			inW[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			inU[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			tanhW[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			tanhU[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			outW[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			outU[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			
			forgetBias[i] = 0.0f;
			inputBias[i] = 0.0f;
			tanhBias[i] = 0.0f;
			outBias[i] = 0.0f;
		}
		
		forgetLearnRate = new float[size];
		inputLearnRate = new float[size];
		tanhLearnRate = new float[size];		
		outLearnRate = new float[size];
		
		forgetMoment = new float[size];
		inputMoment = new float[size];
		tanhMoment = new float[size];		
		outMoment = new float[size];
	}
	
	
	private static float[] hadamardOperator(float[] x, float[] y) {				//element-wise multiplication
		if(x.length != y.length) {
			throw new ArithmeticException("Invalid Size Arrays");
		}
		float[] result = new float[x.length];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = x[i] * y[i];
		}
		
		return result;
	} 
	
	private static float[] elementSumm(float[] x, float[] y) {				//element-wise addition
		if(x.length != y.length) {
			throw new ArithmeticException("Invalid Size Arrays");
		}
		float[] result = new float[x.length];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = x[i] + y[i];
		}
		
		return result;
	}
	
	@Override
	public void setOldOutput(float[] oldOutput) {		
		this.oldOutput = oldOutput;
	}
	
	@Override
	public void setOldCondition(float[] oldCondition) {
		this.oldCondition = oldCondition;
	}

	@Override
	public float[] output() {		
		return output;
	}

	@Override
	public void input(float[] data) {		
		input = data;
	} 
	
	@Override
	public void work() {		
		//TODO main calculations	
		
		float[] forgetRes = new float[vector_size];
		float[] inRes = new float[vector_size];
		float[] tanhRes = new float[vector_size];
		float[] outRes = new float[vector_size];
		
		float[] forgetArg = new float[vector_size];
		float[] inArg = new float[vector_size];
		float[] tanhArg = new float[vector_size];
		float[] outArg = new float[vector_size];
		
		for(int i = 0; i < vector_size; i++) {
			forgetArg[i] = forgetW[i] * input[i] + forgetU[i] * oldOutput[i] + forgetBias[i]; 
			inArg[i] = inW[i] * input[i] + inU[i] * oldOutput[i] + inputBias[i]; 
			tanhArg[i] = tanhW[i] * input[i] + tanhU[i] * oldOutput[i] + tanhBias[i];
			outArg[i] = outW[i] * input[i] + outU[i] * oldOutput[i] + outBias[i];
		}
		
		forgetRes = sigmoid(forgetArg);
		inRes = sigmoid(inArg);
		tanhRes = tanh(tanhArg);
		outRes = sigmoid(outArg);
		
		condition = elementSumm(hadamardOperator(tanhRes, inRes), hadamardOperator(forgetRes, oldCondition));
		output = hadamardOperator(tanh(condition), outRes);
	}
	
	@Override
	public float[] condition() {
		return condition;
	}
	
	//sigmoid, tanh functions and their derivatives
	private static float[] sigmoid(float[] data) {
		float[] result = new float[data.length];
		for(int i = 0; i < data.length; i++) {
			result[i] = (float) (1 / (1 + (Math.pow(Math.E,-(data[i])))));
		}
		return result;
	}
	
	private static float[] tanh(float[] data) {
		float[] result = new float[data.length];
		for(int i = 0; i < data.length; i++) {
			result[i] = (float) ((2 / (1 + (Math.pow(Math.E,-(2 * data[i]))))) - 1);
		}
		return result;
	}
	
	private static float[] sigmoidDerivative(float[] data) {
		float[] result = new float[data.length];
		for(int i = 0; i < data.length; i++) {
			result[i] = (float) ((1 - data[i]) * data[i]);
		}
		return result;
	}
	
	private static float[] tanhDerivative(float[] data) {
		float[] result = new float[data.length];
		for(int i = 0; i < data.length; i++) {
			result[i] = (float) (1 - Math.pow(data[i], 2));
		}
		return result;
	}
}
