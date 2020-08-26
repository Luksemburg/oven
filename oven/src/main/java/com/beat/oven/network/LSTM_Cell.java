package com.beat.oven.network;

import java.util.Random;

public class LSTM_Cell implements ICell {
	
	public final static float STUB = 0.0f;
	
	private float minWeightInit = 0.0f;
	private float maxWeightInit = 2.0f;
	
	private float learnRate = 0.1f;
	
	private int vector_size;
	
	private float oldState[];		// C t-1
	private float oldOutput[];			// h t-1
	
	private float input[];			// X
	private float state[];		// C
	private float output[];			// h
	
	private float F_W[];		// sigma 1st layer weight for input vector
	private float I_W[];			// sigma 2st layer weight for input vector
	private float A_W[];			//tanh layer weight for input vector
	private float O_W[];			// sigma 4st layer weight for input vector
	
	private float F_U[];		// sigma 1st layer weight for memory h_t-1 previous cell		
	private float I_U[];			// sigma 2st layer weight for memory h_t-1 previous cell		
	private float A_U[];			//tanh layer weight for memory h_t-1 previous cell		
	private float O_U[];			// sigma 4st layer weight for memory h_t-1 previous cell	
	
	
	private float F_Bias[];			// f_t
	private float I_Bias[];			// i_t
	private float A_Bias[];			// a_t
	private float O_Bias[];			// o_t
	
	private float F_LearnRate[];			// f_t
	private float I_LearnRate[];				// i_t
	private float A_LearnRate[];				// a_t
	private float O_LearnRate[];				// o_t
	
	private float F_Moment[];			// f_t
	private float I_Moment[];			// i_t
	private float A_Moment[];				// a_t
	private float O_Moment[];				// o_t
	
	
	//deltas
	// TODO init at constructor
	private float deltaOut[];
	private float deltaT[];
	private float deltaState[];
	
	private float deltaF_Gate[];
	private float deltaI_Gate[];
	private float deltaA_Gate[];
	private float deltaO_Gate[];
	
	private float deltaX[];
	
	private float deltaF_W[];		
	private float deltaI_W[];			
	private float deltaA_W[];			
	private float deltaO_W[];			
	
	private float deltaF_U[];				
	private float deltaI_U[];					
	private float deltaA_U[];				
	private float deltaO_U[];				
	
	private float deltaF_Bias[];			
	private float deltaI_Bias[];			
	private float deltaA_Bias[];			
	private float deltaO_Bias[];			
	
	private float deltaAllFutureGates[][];			//	order: F (forget), I (input), A (activation), O (output)
	
	private float pastDeltaOut[];
	private float futureDeltaOut[];
	
	private float futureState[];
	private float futureForget[];		
	
	public LSTM_Cell(int size) {
		super();
		this.vector_size = size;
		
		Random random = new Random();
		
		input = new float[size];
		output = new float[size];
		state = new float[size];
		
		F_W = new float[size];
		I_W = new float[size];
		A_W = new float[size];
		O_W = new float[size];
		
		F_U = new float[size];				
		I_U = new float[size];				
		A_U = new float[size];				
		O_U = new float[size];					
		
		F_Bias = new float[size];
		I_Bias = new float[size];
		A_Bias = new float[size];		
		O_Bias = new float[size];
		
		//Init
		for(int i = 0; i < size; i++) {
			F_W[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			I_W[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			A_W[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			O_W[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			
			F_U[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;			
			I_U[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;			
			A_U[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;			
			O_U[i] = random.nextFloat() * (maxWeightInit - minWeightInit) + minWeightInit;
			
			F_Bias[i] = 0.0f;
			I_Bias[i] = 0.0f;
			A_Bias[i] = 0.0f;
			O_Bias[i] = 0.0f;
		}
		
		F_LearnRate = new float[size];
		I_LearnRate = new float[size];
		A_LearnRate = new float[size];		
		O_LearnRate = new float[size];
		
		F_Moment = new float[size];
		I_Moment = new float[size];
		A_Moment = new float[size];		
		O_Moment = new float[size];
	}
	
	
	
	public float[] getPastDeltaOut() {
		return pastDeltaOut;
	}
	
	public void setFutureDeltaOut(float[] futureDeltaOut) {
		this.futureDeltaOut = futureDeltaOut;
	}
	
	public void setFutureState(float[] futureState) {
		this.futureState = futureState;
	}
	
	public void setFutureForget(float[] futureForget) {
		this.futureForget = futureForget;
	}
	
	@Override
	public void setOldOutput(float[] oldOutput) {		
		this.oldOutput = oldOutput;
	}
	
	@Override
	public void setOldState(float[] oldState) {
		this.oldState = oldState;
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
	public float[] state() {
		return state;
	}
	
	@Override
	public void work() {				
		
		float[] forgetRes = new float[vector_size];
		float[] inRes = new float[vector_size];
		float[] tanhRes = new float[vector_size];
		float[] outRes = new float[vector_size];
		
		float[] forgetArg = new float[vector_size];
		float[] inArg = new float[vector_size];
		float[] tanhArg = new float[vector_size];
		float[] outArg = new float[vector_size];
		
		for(int i = 0; i < vector_size; i++) {
			forgetArg[i] = F_W[i] * input[i] + F_U[i] * oldOutput[i] + F_Bias[i]; 
			inArg[i] = I_W[i] * input[i] + I_U[i] * oldOutput[i] + I_Bias[i]; 
			tanhArg[i] = A_W[i] * input[i] + A_U[i] * oldOutput[i] + A_Bias[i];
			outArg[i] = O_W[i] * input[i] + O_U[i] * oldOutput[i] + O_Bias[i];
		}
		
		forgetRes = sigmoid(forgetArg);
		inRes = sigmoid(inArg);
		tanhRes = tanh(tanhArg);
		outRes = sigmoid(outArg);
		
		state = elementSumm(hadamardOperator(tanhRes, inRes), hadamardOperator(forgetRes, oldState));
		output = hadamardOperator(tanh(state), outRes);
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
}
