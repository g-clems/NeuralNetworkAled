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

public class Case {
	
	private CaseType caseType = CaseType.FLAG_0;
	private boolean discovered = false;
	private boolean flagged = false;
	
	public CaseType getType() {
		return this.caseType;
	}
	
	public void setType(CaseType caseType) {
		this.caseType = caseType;
	}
	
	public void increaseFlag() {
		caseType = caseType.getNextFlag();
	}
	
	public boolean isDiscovered() {
		return this.discovered;
	}
	
	public boolean discover() {
		if(this.discovered) return false;
		this.discovered = true;
		return true;
	}
	
	public boolean isFlagged() {
		return this.flagged;
	}
	
	public boolean flag() {
		if(this.discovered) return false;
		this.flagged = !this.flagged;
		return true;
	}

}
