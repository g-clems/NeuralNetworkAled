package net.feedthemadness.ai.neuralnetwork.ai.network.mutation;

import net.feedthemadness.ai.neuralnetwork.ai.network.EvolutiveNetwork;

public interface IMutation {
	
	void mutate(EvolutiveNetwork[] networks);
}
