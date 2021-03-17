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
