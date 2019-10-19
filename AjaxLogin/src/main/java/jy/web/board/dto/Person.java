package jy.web.board.dto;

public class Person {
	private int pcode;
	private String pid;
	private String ppw;
	private String pname;
	private String pemail;
	private int pgrade;
	
	public Person() {
	
	}
	public Person(int pgrade) {
		this.pgrade = pgrade;
	}
	public Person(String pid, String ppw, String pname, String pemail) {
		super();
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pemail = pemail;
		this.pgrade = 1;
	}
	public Person(int pcode, String pid, String ppw, String pname, String pemail, int pgrade) {
		this.pcode = pcode;
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pemail = pemail;
		this.pgrade = pgrade;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPpw() {
		return ppw;
	}
	public void setPpw(String ppw) {
		this.ppw = ppw;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public int getPgrade() {
		return pgrade;
	}
	public void setPgrade(int pgrade) {
		this.pgrade = pgrade;
	}

	@Override
	public String toString() {
		return "Person [pcode=" + pcode + ", pid=" + pid + ", ppw=" + ppw + ", pname=" + pname + ", pemail=" + pemail
				+ ", pgrade=" + pgrade + "]";
	}
	
	
	
}