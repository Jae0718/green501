package jy.web.board.dto;

public class Board {
	private int bcode;
	private String bcategory;
	private String btitle;
	private int bcount;
	
	public Board() {
		
	}
	public Board(int bcode, String bcategory, String btitle, int bcount) {
		super();
		this.bcode = bcode;
		this.bcategory = bcategory;
		this.btitle = btitle;
		this.bcount = bcount;
	}

	public Board(String bcategory, String btitle) {
		super();
		this.bcategory = bcategory;
		this.btitle = btitle;
	}

	public int getBcode() {
		return bcode;
	}
	public void setBcode(int bcode) {
		this.bcode = bcode;
	}
	public String getBcategory() {
		return bcategory;
	}
	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	@Override
	public String toString() {
		return "Board [bcode=" + bcode + ", bcategory=" + bcategory + ", btitle=" + btitle + ", bcount=" + bcount + "]";
	}

	
	
}
