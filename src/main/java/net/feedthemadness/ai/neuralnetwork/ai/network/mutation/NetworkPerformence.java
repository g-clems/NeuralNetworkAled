package net.feedthemadness.ai.neuralnetwork.ai.network.mutation;

public class NetworkPerformence {
	
	private float score = 0;
	private int weight = 0;
	
	public Float getScore() {
		return score;
	}
	
	public synchronized void addScore(float score) {
		this.score = ((this.score * this.weight) + score) / (this.weight + 1);
		this.weight++;
	}

}
