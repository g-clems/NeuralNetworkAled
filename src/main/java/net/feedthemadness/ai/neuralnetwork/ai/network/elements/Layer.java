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
