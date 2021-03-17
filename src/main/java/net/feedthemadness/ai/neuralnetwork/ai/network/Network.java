package net.feedthemadness.ai.neuralnetwork.ai.network;

import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Layer;

public class Network {
	
	protected Layer[] layers;
	
	private float[] input;
	private float[] output;
	
	public Network(Layer[] layers) {
		this.layers = layers;
	}
	
	public Network(NetworkSettings settings) {
		layers = new Layer[settings.getDef().length];
		
		layers[0] = new Layer(settings.getDef()[0], settings.getInputSize());
		for (int i = 1 ; i < settings.getDef().length ; i++) {
			layers[i] = new Layer(settings.getDef()[i], settings.getDef()[i-1]);
		}
	}

	public Layer[] getLayers() {
		return layers;
	}
	
	public float[] getInput() {
		return input;
	}
	
	public void setInput(float[] input) {
		this.input = input;
	}
	
	public float[] getOutput() {
		return output;
	}
	
	public void compute() {
		float[] layerActivation = input;
		
//		for(int i = 0 ; i < layers.length ; i++) {
//			layers[i].computeLayer(layerActivation);
//			layerActivation = layers[i].getLayerActivation();
//		}
		// Replaced by while loop for optimisation
		
		int i = 0;
		int loop = layers.length;
		while (i < loop) {
			Layer layer = layers[i];
			layer.computeLayer(layerActivation);
			layerActivation = layer.getLayerActivation();
			
			i++;
		}
		
		output = layerActivation;
	}
	
}
