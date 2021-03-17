package net.feedthemadness.ai.neuralnetwork.ai.network.elements;

import java.util.Arrays;
import java.util.Random;

import net.feedthemadness.ai.neuralnetwork.ai.utils.Maths;

public class Node {
	
	protected float[] inputWeight;
	protected float bias = 0;
	
	protected float activation = 0.5f;
	
	public Node(float[] settings) {
		this.inputWeight = Arrays.copyOf(settings, settings.length - 1);
		this.bias = settings[settings.length - 1];
	}
	
	public Node(int inputs) {
		inputWeight = new float[inputs];
		
		Random random = new Random();
		for(int i = 0 ; i < inputWeight.length ; i++) {
			inputWeight[i] = random.nextFloat();
		}
	}
	
	public float[] getInputWeight() {
		return inputWeight;
	}
	
	public void setInputWeight(float[] inputWeight) {
		this.inputWeight = inputWeight;
	}
	
	public float getBias() {
		return bias;
	}
	
	public void setBias(float bias) {
		this.bias = bias;
	}
	
	public float getActivation() {
		return activation;
	}
	
	public void computeActivation(float[] input) {
		float result = 0;
		
//		for (int i = 0 ; i < input.length ; i++) {
//			result += inputWeight[i] * input[i];
//		}
		// Replaced by while loop for optimisation
		
		int i = 0;
		int loop = input.length;
		while (i < loop) {
			result += input[i] * inputWeight[i];
			
			i++;
		}
		
		result -= bias;
		// Maths.sigmoid() old ver -> Math.max(0, result) new ver
		activation = Maths.sigmoid(result);
	}
	
}
