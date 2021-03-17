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
