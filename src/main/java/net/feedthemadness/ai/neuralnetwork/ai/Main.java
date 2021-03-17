package net.feedthemadness.ai.neuralnetwork.ai;

import net.feedthemadness.ai.neuralnetwork.ai.training.MineSweeperTrainer;
import net.feedthemadness.ai.neuralnetwork.ai.utils.TerminalWrapper;

public class Main {
	
	private static TerminalWrapper tw = new TerminalWrapper();
	
	public static void main(String[] args) {
		
		int generationSize = tw.requestInt("Generation size");
		tw.info("Initating evolution with " + generationSize + " networks per generation");
		EvolutionManager em = new EvolutionManager(generationSize, new MineSweeperTrainer(20, 20, 18));
		
		new Thread(em).start();
		
	}
	
	public static TerminalWrapper getTerminal() {
		return tw;
	}
	
}
