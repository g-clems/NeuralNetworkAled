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

public enum CaseType {
	
	FLAG_0('.', 0),
	FLAG_1('1', 1),
	FLAG_2('2', 2),
	FLAG_3('3', 3),
	FLAG_4('4', 4),
	FLAG_5('5', 5),
	FLAG_6('6', 6),
	FLAG_7('7', 7),
	FLAG_8('8', 8),
	BOMB('%', -1);
	
	private final char c;
	private final int typeIndex;
	
	CaseType(char c, int typeIndex) {
		this.c = c;
		this.typeIndex = typeIndex;
	}
	
	public char getChar() {
		return c;
	}
	
	public int getTypeIndex() {
		return typeIndex;
	}
	
	public CaseType getNextFlag() {
		return values()[getTypeIndex() + 1];
	}
}
