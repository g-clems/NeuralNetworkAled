package net.feedthemadness.ai.neuralnetwork.ai.training;

import net.feedthemadness.ai.neuralnetwork.ai.network.Network;
import net.feedthemadness.ai.neuralnetwork.ai.network.NetworkSettings;

public interface ITrainer {
	
	NetworkSettings getNetworkDef();
	
	float run(Network network);

	void runTimedShow(Network network, long interval);
	
	/**
	 * @return The definition of the trainer for it to be shown in the terminal
	 */
	String getDescription();
}
