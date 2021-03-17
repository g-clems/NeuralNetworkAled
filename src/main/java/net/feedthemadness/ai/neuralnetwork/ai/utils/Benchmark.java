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
