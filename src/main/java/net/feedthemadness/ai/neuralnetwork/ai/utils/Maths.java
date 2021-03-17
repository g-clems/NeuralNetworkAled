package net.feedthemadness.ai.neuralnetwork.ai.utils;

public class Maths {
	
	public static float sigmoid(float f) {
		//(1 + E**-x)**-1
		//return (float) (Math.pow(1 + Math.pow(Math.E, -f), -1));
		return (float) (1 / (1 + Math.pow(Math.E, -f)));
	}
	
}
