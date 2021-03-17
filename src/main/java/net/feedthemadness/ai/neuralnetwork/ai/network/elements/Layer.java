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

package net.feedthemadness.ai.neuralnetwork.ai.network.elements;

public class Layer {
	
	private Node[] nodes;
	
	private float[] layerActivation;
	
	public Layer(Node[] nodes) {
		this.nodes = nodes;
		this.layerActivation = new float[nodes.length];
	}
	
	public Layer(int size, int previousLayerSize) {
		nodes = new Node[size];
		layerActivation = new float[size];
		
		for(int i = 0 ; i < size ; i++) {
			Node newNode = new Node(previousLayerSize);
			nodes[i] = newNode;
			layerActivation[i] = newNode.getActivation();
		}
	}
	
	public Node[] getNodes() {
		return nodes;
	}
	
	public float[] getLayerActivation() {
		return layerActivation;
	}
	
	public void computeLayer(float[] previousLayerActivation) {
		
//		for (int i = 0 ; i < nodes.length ; i++) {
//			Node node = nodes[i];
//			node.computeActivation(previousLayerActivation);
//			layerActivation[i] = node.getActivation();
//		}
		// Replaced by while loop for optimisation
		
		int i = 0;
		int loop = nodes.length;
		while (i < loop) {
			Node node = nodes[i];
			node.computeActivation(previousLayerActivation);
			layerActivation[i] = node.getActivation();
			
			i++;
		}
	}
	
}
