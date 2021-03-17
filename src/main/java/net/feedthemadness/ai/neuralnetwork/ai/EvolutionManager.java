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

import java.util.Arrays;

import net.feedthemadness.ai.neuralnetwork.ai.network.EvolutiveNetwork;
import net.feedthemadness.ai.neuralnetwork.ai.network.mutation.Mutation;
import net.feedthemadness.ai.neuralnetwork.ai.network.thread.ThreadManager;
import net.feedthemadness.ai.neuralnetwork.ai.training.ITrainer;
import net.feedthemadness.ai.neuralnetwork.ai.training.MineSweeperTrainer;
import net.feedthemadness.ai.neuralnetwork.ai.utils.Benchmark;

public class EvolutionManager implements Runnable {
	
	private EvolutiveNetwork[] generation;
	
	private ITrainer trainer;
	private Mutation mutation = new Mutation(20, 1f, 100, 2f);
	
	private int networkGeneration = 0;
	
	public EvolutionManager(int genSize, ITrainer trainer) {
		this.generation = new EvolutiveNetwork[genSize];
		this.trainer = trainer;
		
		for(int i = 0 ; i < genSize ; i++) {
			generation[i] = new EvolutiveNetwork(trainer.getNetworkDef());
		}
	}
	
	@Override
	public void run() {
		boolean stopping = false;
		
		while (!stopping) {
			String cmd = Main.getTerminal().requestString("");
			String[] args = cmd.split(" ");
			
			switch (args[0]) {
					
				case "run":
					if (args.length < 4) break;
					try {
						int generations = Integer.parseInt(args[1]);
						int iterations = Integer.parseInt(args[2]);
						int threads = Integer.parseInt(args[3]);
						runGen(generations, iterations, threads);
					} catch (NumberFormatException e) {
						Main.getTerminal().error("Number format");
					}
					break;
					
				case "softmutation":
					if (args.length < 3) break;
					try {
						int factorAmmount = Integer.parseInt(args[1]);
						float factorMultiplyer = Float.parseFloat(args[2]);
						
						this.mutation.setSoftMutationFactorAmmount(factorAmmount);
						this.mutation.setSoftMutationFactorMultiplyer(factorMultiplyer);
						
						Main.getTerminal().info("Updating soft mutation :"
								+ " factor ammount : " + factorAmmount
								+ ", factor multiplyer : " + factorMultiplyer);
					} catch (NumberFormatException e) {
						Main.getTerminal().error("Number format");
					}
					break;
					
				case "hardmutation":
					if (args.length < 3) break;
					try {
						int factorAmmount = Integer.parseInt(args[1]);
						float factorMultiplyer = Float.parseFloat(args[2]);
						
						this.mutation.setHardMutationFactorAmmount(factorAmmount);
						this.mutation.setHardMutationFactorMultiplyer(factorMultiplyer);
						
						Main.getTerminal().info("Updating hard mutation :"
								+ " factor ammount : " + factorAmmount
								+ ", factor multiplyer : " + factorMultiplyer);
					} catch (NumberFormatException e) {
						Main.getTerminal().error("Number format");
					}
					break;
					
				case "trainer":
					if (args.length < 4) break;
					try {
						int xSize = Integer.parseInt(args[1]);
						int ySize = Integer.parseInt(args[2]);
						int percentage = Integer.parseInt(args[3]);
						this.trainer = new MineSweeperTrainer(xSize, ySize, percentage);
						Main.getTerminal().info("Updating trainer :\n"
								+ this.trainer.getDescription());
					} catch (NumberFormatException e) {
						Main.getTerminal().error("Number format");
					}
					break;
					
				case "show":
					if (args.length < 2) break;
					try {
						sortGen();
						long interval = Long.parseLong(args[1]);
						trainer.runTimedShow(generation[0], interval);
					} catch (NumberFormatException e) {
						Main.getTerminal().error("Number format");
					}
					break;
					
				case "info":
					printInfo();
					break;
					
				case "scores":
					sortGen();
					printScores();
					break;
					
				case "ages":
					sortGen();
					printAges();
					break;
					
				case "export":
					break;
					
				case "import":
					break;
					
				case "stop":
					stopping = true;
					break;
				
				default:
					break;
			}
		}
	}
	
	private void runGen(int generations, int iterationPerGen, int threads) {
		Main.getTerminal().info("Running " + generations + " generations with "
				+ iterationPerGen + " iterations per generation on " + threads + " threads");
		
		Benchmark[] benchmark = new Benchmark[] {new Benchmark(generations), new Benchmark(1)};
		
		for (int i = 0 ; i < generations ; i++) {
			Main.getTerminal().update("Mutating for generation " + (i + 1) + "/" + generations);
			mutation.mutate(generation);
			
			Main.getTerminal().update("Computing generation " + (i + 1) + "/" + generations);
			
			ThreadManager threadManager = new ThreadManager(threads);
			
			for (EvolutiveNetwork network : generation) {
				
				for (int j = 0 ; j < iterationPerGen ; j++) {
					
					threadManager.addTask(() -> {
						float score = trainer.run(network);
						network.getPerf().addScore(score);
					});
				}
				
				network.increaseAge();
			}
			threadManager.run();
			this.networkGeneration++;
			
			benchmark[0].update();
		}
		benchmark[1].update();
		
		Main.getTerminal().info("Done. Took " + Math.round((benchmark[1].getAverageDelta() / 1000) / 60) + " minutes");
		Main.getTerminal().info("Generation compute time : min/avg/med/max = "
				+ Math.round(benchmark[0].getMinDelta()) + '/'
				+ Math.round(benchmark[0].getAverageDelta()) + '/'
				+ Math.round(benchmark[0].getMedianDelta()) + '/'
				+ Math.round(benchmark[0].getMaxDelta()) + " ms");
	}
	
	private void sortGen() {
		Arrays.sort(generation, (o1, o2) -> o2.getPerf().getScore().compareTo(o1.getPerf().getScore()));
	}
	
	public void printInfo() {
		Main.getTerminal().info("Generation : " + this.networkGeneration);
		Main.getTerminal().info("Best score : " + generation[0].getPerf().getScore());
	}
	
	public void printScores() {
		sortGen();
		StringBuilder builder = new StringBuilder();
		
		builder.append("Scores of the generation :\n");
		for(int i = 0 ; i < generation.length ; i++) {
			builder.append(" - ").append(i + 1).append(": ")
					.append(Math.round(generation[i].getPerf().getScore()));
			if((i + 1) % 20 == 0) builder.append('\n');
		}
		
		Main.getTerminal().info(builder.toString());
	}
	
	public void printAges() {
		sortGen();
		StringBuilder builder = new StringBuilder();
		
		builder.append("Ages of the generation :\n");
		for(int i = 0 ; i < generation.length ; i++) {
			builder.append(" - ").append(i + 1).append(": ")
					.append(generation[i].getAge());
			if((i + 1) % 20 == 0) builder.append('\n');
		}
		
		Main.getTerminal().info(builder.toString());
	}
	
}
