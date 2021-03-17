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
