package net.feedthemadness.ai.neuralnetwork.ai.network.mutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.feedthemadness.ai.neuralnetwork.ai.Main;
import net.feedthemadness.ai.neuralnetwork.ai.network.EvolutiveNetwork;
import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Layer;
import net.feedthemadness.ai.neuralnetwork.ai.network.elements.Node;
import net.feedthemadness.ai.neuralnetwork.ai.network.serialization.SerializedEvolutiveNetwork;
import net.feedthemadness.ai.neuralnetwork.ai.network.thread.ThreadManager;

public class Mutation implements IMutation {
	
	private int softMutationFactorAmmount = 2;
	private float softMutationFactorMultiplyer = 0.2f;
	private int hardMutationFactorAmmount = 20;
	private float hardMutationFactorMultiplyer = 2f;
	
	public Mutation() {}
	
	public Mutation(int softMutationFactorAmmount, float softMutationFactorMultiplyer,
			int hardMutationFactorAmmount, float hardMutationFactorMultiplyer) {
		this.softMutationFactorAmmount = softMutationFactorAmmount;
		this.softMutationFactorMultiplyer = softMutationFactorMultiplyer;
		this.hardMutationFactorAmmount = hardMutationFactorAmmount;
		this.hardMutationFactorMultiplyer = hardMutationFactorMultiplyer;
	}
	
	public int getSoftMutationFactorAmmount() {
		return softMutationFactorAmmount;
	}
	
	public void setSoftMutationFactorAmmount(int softMutationFactorAmmount) {
		this.softMutationFactorAmmount = softMutationFactorAmmount;
	}
	
	public float getSoftMutationFactorMultiplyer() {
		return softMutationFactorMultiplyer;
	}
	
	public void setSoftMutationFactorMultiplyer(float softMutationFactorMultiplyer) {
		this.softMutationFactorMultiplyer = softMutationFactorMultiplyer;
	}
	
	public int getHardMutationFactorAmmount() {
		return hardMutationFactorAmmount;
	}
	
	public void setHardMutationFactorAmmount(int hardMutationFactorAmmount) {
		this.hardMutationFactorAmmount = hardMutationFactorAmmount;
	}
	
	public float getHardMutationFactorMultiplyer() {
		return hardMutationFactorMultiplyer;
	}
	
	public void setHardMutationFactorMultiplyer(float hardMutationFactorMultiplyer) {
		this.hardMutationFactorMultiplyer = hardMutationFactorMultiplyer;
	}
	
	@Override
	public void mutate(EvolutiveNetwork[] networks) {
		Arrays.sort(networks, (o1, o2) -> o2.getPerf().getScore().compareTo(o1.getPerf().getScore()));
		
		ThreadManager threadManager = new ThreadManager(4);
		
		for (int i = 1 ; i <= networks.length / 4 ; i++) {
			
			//Main.getTerminal().log(message);
			
			EvolutiveNetwork parentNetwork = networks[i - 1];
			//mutate(parentNetwork, this.softMutationFactorAmmount, this.softMutationFactorMultiplyer);
			
			SerializedEvolutiveNetwork parent = new SerializedEvolutiveNetwork(parentNetwork);
			
			EvolutiveNetwork child1 = parent.createNetwork();
			threadManager.addTask(() -> {
				mutate(child1, this.softMutationFactorAmmount, this.softMutationFactorMultiplyer);
			});
			child1.setAge(parentNetwork.getAge());
			networks[(i * 2) - 1] = child1;
			
			EvolutiveNetwork child2 = parent.createNetwork();
			threadManager.addTask(() -> {
				mutate(child2, this.hardMutationFactorAmmount, this.hardMutationFactorMultiplyer);
			});
			networks[(i * 3) - 1] = child2;
			
			if(i * 4 > networks.length) {
				continue;
			}
			
			EvolutiveNetwork child3 = parent.createNetwork();
			threadManager.addTask(() -> {
				mutate(child3, this.hardMutationFactorAmmount, this.hardMutationFactorMultiplyer);
			});
			networks[(i * 4) - 1] = child3;
		}
		
		threadManager.run();
	}
	
	private void mutate(EvolutiveNetwork network, int factorAmmount, float factorMultiplyer) {
		Random random = new Random();
		List<String> mutatedFactors = new ArrayList<>(factorAmmount);
		
		for(int i = 0 ; i < factorAmmount ; i++) {
			boolean mutatable = false;
			String serializedFactor;
			Node randomNode;
			int randomFactorIndex;
			
			do {
				int randomLayerIndex = random.nextInt(network.getLayers().length);
				Layer randomLayer = network.getLayers()[randomLayerIndex];
				int randomNodeIndex = random.nextInt(randomLayer.getNodes().length);
				randomNode = randomLayer.getNodes()[randomNodeIndex];
				randomFactorIndex = random.nextInt(randomNode.getInputWeight().length + 1); // adding 1 for the bias
				
				serializedFactor = new StringBuilder()
						.append(randomLayerIndex).append(";")
						.append(randomNodeIndex).append(";")
						.append(randomFactorIndex).toString();
				
				mutatable = !mutatedFactors.contains(serializedFactor);
				
			} while (mutatable == false);
			
			mutatedFactors.add(serializedFactor);
			
			float mutation = (random.nextFloat() * 2f) - 1f;
			mutation *= factorMultiplyer;
			
			if(randomFactorIndex == 0) {
				float newBias = randomNode.getBias() + mutation;
				randomNode.setBias(newBias);
				
			} else {
				randomFactorIndex--; // removing the bias index;
				float newWeight = randomNode.getInputWeight()[randomFactorIndex] + mutation;
				randomNode.getInputWeight()[randomFactorIndex] = newWeight;
				
			}
		}
		
		network.resetPerf();
	}
	
}
