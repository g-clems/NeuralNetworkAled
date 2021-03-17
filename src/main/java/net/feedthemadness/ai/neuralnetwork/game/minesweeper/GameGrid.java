package net.feedthemadness.ai.neuralnetwork.game.minesweeper;

public class GameGrid {
	
	private Case[][] grid;
	private int x, y;
	
	public GameGrid(int xSize, int ySize) {
		x= xSize;
		y = ySize;
		
		grid = new Case[x][y];
		
		for (int y = 0 ; y < this.y ; y++) {
			for (int x = 0 ; x < this.x ; x++) {
				grid[x][y] = new Case();
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isInGrid(int x, int y) {
		return (x >= 0 && y >= 0 && x < this.x && y < this.y);
	}
	
	public Case getCase(int x, int y) {
		return grid[x][y];
	}
	
	public void print() {
		System.out.println(getGridState());
	}
	
	public void reveal() {
		StringBuilder builder = new StringBuilder();
		
		for (int y = 0 ; y < this.y ; y++) {
			for (int x = 0 ; x < this.x ; x++) {
				Case currentCase = grid[x][y];
				builder.append(currentCase.getType().getChar());
				builder.append(" ");
			}
			builder.append("\n");
		}
		
		System.out.println(builder.toString());
	}
	
	public String getGridState() {
		StringBuilder builder = new StringBuilder();
		
		for (int y = 0 ; y < this.y ; y++) {
			for (int x = 0 ; x < this.x ; x++) {
				Case currentCase = grid[x][y];
				if(!currentCase.isDiscovered()) {
					if(currentCase.isFlagged()) {
						builder.append('F');
					} else {
						builder.append('#');
					}
				} else {
					builder.append(currentCase.getType().getChar());
				}
				builder.append(" ");
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
}
