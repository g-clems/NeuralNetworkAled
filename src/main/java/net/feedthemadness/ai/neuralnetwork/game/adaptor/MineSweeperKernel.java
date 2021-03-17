package net.feedthemadness.ai.neuralnetwork.game.adaptor;

import net.feedthemadness.ai.neuralnetwork.game.minesweeper.Case;
import net.feedthemadness.ai.neuralnetwork.game.minesweeper.GameGrid;

public class MineSweeperKernel extends Kernel {
	
	public void computeKernel(int x, int y, GameGrid gameGrid) {
		float[] result = new float[25 * 3];
		int resultIndex = 0;
		
		for(int j = -2 ; j < 3 ; j++) {
			for(int i = -2 ; i < 3 ; i++) {
				int xOffsetted = x + i;
				int yOffsetted = y + j;
				
				if(!gameGrid.isInGrid(xOffsetted, yOffsetted)) {
					result[resultIndex] = 0;
					result[resultIndex + 1] = 0;
					result[resultIndex + 2] = 0;
					
				} else {
					Case currentCase = gameGrid.getCase(xOffsetted, yOffsetted);
					
					if(currentCase.isDiscovered()) {
						result[resultIndex] = currentCase.getType().getTypeIndex() / 8f;
					} else {
						result[resultIndex] = 1;
					}
					
					result[resultIndex + 1] = currentCase.isDiscovered() ? 1 : 0;
					result[resultIndex + 2] = currentCase.isFlagged() ? 1 : 0;
				}
				
				resultIndex += 3;
			}
		}
		
		kernel = result;
	}
	
}
