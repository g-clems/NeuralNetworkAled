package net.feedthemadness.ai.neuralnetwork.game.minesweeper;

import java.util.Random;

import net.feedthemadness.ai.neuralnetwork.game.adaptor.Kernel;
import net.feedthemadness.ai.neuralnetwork.game.adaptor.MineSweeperKernel;
import net.feedthemadness.ai.neuralnetwork.game.adaptor.ITrainingGame;

public class MineSweeper implements ITrainingGame {
	
	private GameGrid gameGrid;
	
	private int x;
	private int y;
	
	private float toDiscover = 0;
	private float totalToDiscover = 0;
	private float totalBombs = 0;
	private float flags = 0;
	private boolean revealed = false;
	
	public MineSweeper(int xSize, int ySize) {
		this.x = xSize;
		this.y = ySize;
		
		this.gameGrid = new GameGrid(x, y);
	}
	
	public boolean isBomb(int x, int y) {
		return this.gameGrid.getCase(x, y).getType() == CaseType.BOMB;
	}
	
	public boolean isInGrid(int x, int y) {
		return gameGrid.isInGrid(x, y);
	}
	
	public boolean isDiscovered(int x, int y) {
		return gameGrid.getCase(x, y).isDiscovered();
	}
	
	public boolean discover(int x, int y) {
		if(!isInGrid(x, y)) return false;
		Case currentCase = gameGrid.getCase(x, y);
		
		if(currentCase.isDiscovered()) return false;
		if(currentCase.isFlagged()) flag(x, y);
		
		currentCase.discover();
		this.toDiscover--;
		
		if (this.toDiscover == 0) {
			this.revealed = true;
			return true;
			
		} else if (currentCase.getType() == CaseType.BOMB) {
			this.revealed = true;
			return true;
			
		} else if (currentCase.getType() == CaseType.FLAG_0) {
			discover(x-1, y-1);
			discover(x, y-1);
			discover(x+1, y-1);
			discover(x-1, y);
			discover(x+1, y);
			discover(x-1, y+1);
			discover(x, y+1);
			discover(x+1, y+1);
		}
		
		return false;
	}
	
	public boolean flag(int x, int y) {
		gameGrid.getCase(x, y).flag();
		
		if (this.flags < this.totalBombs) {
			this.flags++;
		} else if (gameGrid.getCase(x, y).isFlagged()) {
			this.flags--;
		}
		
		return (toDiscover == 0);
	}
	
	public void init(int bombRatio) {
		Random random = new Random();
		int bombs = x * y * bombRatio / 100;
		this.totalToDiscover = x * y - bombs;
		this.toDiscover = this.totalToDiscover;
		this.totalBombs = bombs;
		
		while (bombs != 0) {
			int randomX = random.nextInt(x);
			int randomY = random.nextInt(y);
			
			if(isBomb(randomX, randomY)) continue;
			
			this.gameGrid.getCase(randomX, randomY).setType(CaseType.BOMB);
			
			increaseFlag(randomX-1, randomY-1);
			increaseFlag(randomX, randomY-1);
			increaseFlag(randomX+1, randomY-1);
			increaseFlag(randomX-1, randomY);
			increaseFlag(randomX+1, randomY);
			increaseFlag(randomX-1, randomY+1);
			increaseFlag(randomX, randomY+1);
			increaseFlag(randomX+1, randomY+1);
			
			bombs--;
		}
	}
	
	private void increaseFlag(int randomX, int randomY) {
		if((isInGrid(randomX, randomY)) && !(isBomb(randomX, randomY))) {
			gameGrid.getCase(randomX, randomY).increaseFlag();
		}
	}
	
	public void print() {
		if(this.revealed) {
			gameGrid.reveal();
			return;
		}
		gameGrid.print();
	}
	
	public Kernel getKernel(int x, int y) {
		MineSweeperKernel kernel = new MineSweeperKernel();
		kernel.computeKernel(x, y, gameGrid);
		
		return kernel;
	}
	
	public String getGridState() {
		return this.gameGrid.getGridState();
	}
	
	@Override
	public float getScore() {
		return ((1f - (this.toDiscover / this.totalToDiscover)) * 100f);
	}
	
}
