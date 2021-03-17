/*
 * NeuralNetworkAled - A shitty neural network handmade in Java.
 * Copyright (C) 2021  Clément Sol
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
