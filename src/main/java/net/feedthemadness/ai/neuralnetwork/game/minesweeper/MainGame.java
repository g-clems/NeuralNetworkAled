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

import net.feedthemadness.ai.neuralnetwork.ai.utils.TerminalWrapper;

public class MainGame implements Runnable {
	
	@Override
	public void run() {
		
		MineSweeper mineSweeper = new MineSweeper(12, 12);
		mineSweeper.init(15);
		mineSweeper.print();
		
		TerminalWrapper tm = new TerminalWrapper();
		
		boolean stopping = false;
		
		while (!stopping) {
			
			String cmd = tm.requestString("");
			String[] args = cmd.split(" ");
			
			switch (args[0]) {
			case "d":
				int x = Integer.parseInt(args[1]);
				int y = Integer.parseInt(args[2]);
				mineSweeper.discover(x, y);
				mineSweeper.print();
				break;
				
			case "f":
				x = Integer.parseInt(args[1]);
				y = Integer.parseInt(args[2]);
				mineSweeper.flag(x, y);
				mineSweeper.print();
				break;
				
			case "stop":
				stopping = true;
				break;

			default:
				break;
			}
		}
		
	}
	
}
