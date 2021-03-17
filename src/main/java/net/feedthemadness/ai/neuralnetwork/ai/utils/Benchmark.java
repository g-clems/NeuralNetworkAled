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

package net.feedthemadness.ai.neuralnetwork.ai.utils;

public class Benchmark {
	
	private long[] deltas;
	private int currentIndex = 0;
	
	private long lastTimestamp;
	
	private long min = 0;
	private long max = 0;
	
	public Benchmark() {
		this(100);
	}
	
	public Benchmark(int sampleAmmount) {
		deltas = new long[sampleAmmount];
		lastTimestamp = System.currentTimeMillis();
	}
	
	public int getCurrentIndex() {
		return this.currentIndex;
	}
	
	public double getMinDelta() {
		return min;
	}
	
	public double getAverageDelta() {
		double average = 0;
		int sampleAmmount = deltas.length;
		
		for(int i = 0 ; i < deltas.length ; i++) {
			if(deltas[i] == 0) sampleAmmount--;
			average += deltas[i];
		}
		average /= sampleAmmount;
		
		return average;
	}
	
	public double getMedianDelta() {
		double median = deltas[(deltas.length / 2) - 1];
		
		if (deltas.length % 2 == 0) {
			median += deltas[deltas.length / 2];
			median /= 2;
		}
		
		return median;
	}
	
	public double getMaxDelta() {
		return max;
	}
	
	public void update() {
		long currentTimestamp = System.currentTimeMillis();
		long delta = currentTimestamp - lastTimestamp;
		
		deltas[currentIndex] = delta;
		lastTimestamp = currentTimestamp;
		
		currentIndex++;
		currentIndex %= deltas.length;
		
		if (delta < min || min == 0) min = delta;
		if (delta > max) max = delta;
	}
	
}
