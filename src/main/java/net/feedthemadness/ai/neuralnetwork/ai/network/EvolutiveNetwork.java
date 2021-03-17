package net.feedthemadness.ai.neuralnetwork.ai.network;

import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Layer;
import net.feedthemadness.ai.neuralnetwork.ai.network.mutation.IMutative;
import net.feedthemadness.ai.neuralnetwork.ai.network.mutation.NetworkPerformence;

public class EvolutiveNetwork extends Network implements IMutative {
	
	private NetworkPerformence perf = new NetworkPerformence();
	private int age = 0;
	
	public EvolutiveNetwork(Layer[] layers) {
		super(layers);
	}
	
	public EvolutiveNetwork(NetworkSettings settings) {
		super(settings);
	}
	
	public NetworkPerformence getPerf() {
		return perf;
	}
	
	public void resetPerf() {
		this.perf = new NetworkPerformence();
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void increaseAge() {
		this.age++;
	}
	
}
