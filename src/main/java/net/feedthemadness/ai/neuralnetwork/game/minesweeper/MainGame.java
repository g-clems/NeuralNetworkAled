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
