package utils;

public class TagNode {
	private int addr;
	private int data;
	private int nextAddr;
	

	public TagNode() {
	}

	public TagNode(int addr, int data, int nextAddr) {
		super();
		this.addr = addr;
		this.data = data;
		this.nextAddr = nextAddr;
	}

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getNextAddr() {
		return nextAddr;
	}

	public void setNextAddr(int nextAddr) {
		this.nextAddr = nextAddr;
	}

}
