package net.feedthemadness.ai.neuralnetwork.ai.training;

import net.feedthemadness.ai.neuralnetwork.ai.network.Network;
import net.feedthemadness.ai.neuralnetwork.ai.network.NetworkSettings;
import net.feedthemadness.ai.neuralnetwork.game.minesweeper.MineSweeper;

public class MineSweeperTrainer implements ITrainer {
	
	private int xSize = 25;
	private int ySize = 25;
	private int percentage = 15;
	
	private double maxTry;
	private String previousGridState;
	
	public MineSweeperTrainer() {
		this.maxTry = Math.pow(xSize * ySize, 2);
	}
	
	public MineSweeperTrainer(int xSize, int ySize, int percentage) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.percentage = percentage;
		
		this.maxTry = Math.pow(xSize * ySize, 2);
	}
	
	public int getxSize() {
		return xSize;
	}
	
	public void setxSize(int xSize) {
		this.xSize = xSize;
	}
	
	public int getySize() {
		return ySize;
	}
	
	public void setySize(int ySize) {
		this.ySize = ySize;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public NetworkSettings getNetworkDef() {
		return new NetworkSettings(25 * 3, new int[] {120, 120, 60, 10, 2});
	}
	
	@Override
	public void runTimedShow(Network network, long interval) {
		play(network, true, interval);
	}
	
	@Override
	public float run(Network network) {
		return play(network, false, 0);
	}
	
	private float play(Network network, boolean show, long interval) {
		MineSweeper ms = new MineSweeper(xSize, ySize);
		ms.init(percentage);
		
		this.previousGridState = ms.getGridState();
		String gridState = ms.getGridState() + '0';
		
		boolean gameOver = false;
		double tries = 0;
		
		int x = 0;
		int y = 0;
		
		while (!(gameOver) && (tries < maxTry) && !(gridState.equals(previousGridState)) ) {
			
			network.setInput(ms.getKernel(x, y).getKernel());
			network.compute();
			
			float[] output = network.getOutput();
			
			if(output[0] >= 0.5f || output[1] >= 0.5f) {
				if(output[0] >= output[1]) {
					gameOver = ms.discover(x, y);
				} else {
					gameOver = ms.flag(x, y);
				}
			}
			
			do {
				x++;
				if(x >= xSize) {
					y++;
					if(y >= ySize) {
						this.previousGridState = gridState;
						gridState = ms.getGridState();
					}
				}
				x %= xSize;
				y %= ySize;
			} while (ms.isDiscovered(x, y));
			
			tries++;
			
			if(show) {
				ms.print();
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ms.getScore();
	}
	
	@Override
	public String getDescription() {
		return "MineSweeper Trainer : size = " + this.xSize + " x " + this.ySize + " percentage (bombs) = " + this.percentage;
	}
}
