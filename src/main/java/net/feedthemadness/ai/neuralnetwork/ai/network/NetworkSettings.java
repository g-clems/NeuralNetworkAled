package net.feedthemadness.ai.neuralnetwork.ai.network;

public class NetworkSettings {
	
	private int input;
	private int[] def;
	
	public NetworkSettings(Network network) {
		this.input = network.getInput().length;
		this.def = new int[network.getLayers().length];
	}
	
	public NetworkSettings(int input, int[] def) {
		this.input = input;
		this.def = def;
	}
	
	public int getInputSize() {
		return input;
	}
	
	public int[] getDef() {
		return def;
	}

}
