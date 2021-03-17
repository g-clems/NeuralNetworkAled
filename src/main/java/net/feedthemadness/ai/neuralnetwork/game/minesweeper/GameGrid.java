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
