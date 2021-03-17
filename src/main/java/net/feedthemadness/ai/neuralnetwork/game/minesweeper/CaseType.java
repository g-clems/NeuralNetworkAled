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
