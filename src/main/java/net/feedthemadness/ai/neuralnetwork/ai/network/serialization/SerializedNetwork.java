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

package net.feedthemadness.ai.neuralnetwork.ai.network.serialization;

import java.util.Arrays;

import net.feedthemadness.ai.neuralnetwork.ai.network.Network;
import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Layer;
import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Node;

public class SerializedNetwork {
	
	private float[][][] networkDefinition;
	
	public SerializedNetwork() {}
	
	public SerializedNetwork(String serializedNetwork) {
		load(serializedNetwork);
	}
	
	public SerializedNetwork(Network network) {
		load(network);
	}
	
	public SerializedNetwork(SerializedNetwork serializedNetwork) {
		load(serializedNetwork);
	}
	
	public void load(String serializedNetwork) {
		//TODO
	}
	
	public void load(Network network) {
		
		Layer[] layers = network.getLayers();
		
		this.networkDefinition = new float[layers.length][][];
		
		for(int i = 0 ; i < layers.length ; i++) {
			
			Node[] nodes = layers[i].getNodes();
			
			this.networkDefinition[i] = new float[nodes.length][];
			
			for(int j = 0 ; j < nodes.length ; j++) {
				
				float[] inputWeight = nodes[j].getInputWeight();
				float[] settings = Arrays.copyOf(inputWeight, inputWeight.length + 1);
				settings[inputWeight.length] = nodes[j].getBias();
				
				this.networkDefinition[i][j] = settings;
			}
		}
		
	}
	
	public void load(SerializedNetwork serializedNetwork) {
		this.networkDefinition = Arrays.copyOf(serializedNetwork.networkDefinition, serializedNetwork.networkDefinition.length);
	}
	
	public Network createNetwork() {
		return new Network(createLayers());
	}
	
	protected Layer[] createLayers() {
		Layer[] layers = new Layer[this.networkDefinition.length];
		
		for(int i = 0 ; i < this.networkDefinition.length ; i++) {
			Node[] nodes = new Node[this.networkDefinition[i].length];
			
			for (int j = 0 ; j < this.networkDefinition[i].length ; j++) {
				nodes[j] = new Node(this.networkDefinition[i][j]);
			}
			layers[i] = new Layer(nodes);
		}
		
		return layers;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("network=[");
		
		for(int i = 0 ; i < this.networkDefinition.length ; i++) {
			
			sb.append("layer=[");
			
			for (int j = 0 ; j < this.networkDefinition[i].length ; j++) {
				
				sb.append("node=");
				sb.append(Arrays.toString(this.networkDefinition[i][j]));
			}
			
			sb.append("]");
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
}
