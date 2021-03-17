package net.feedthemadness.ai.neuralnetwork.ai.network.serialization;

import net.feedthemadness.ai.neuralnetwork.ai.network.EvolutiveNetwork;

public class SerializedEvolutiveNetwork extends SerializedNetwork {
	
	public SerializedEvolutiveNetwork(EvolutiveNetwork network) {
		super(network);
	}
	
	@Override
	public EvolutiveNetwork createNetwork() {
		return new EvolutiveNetwork(createLayers());
	}

}
