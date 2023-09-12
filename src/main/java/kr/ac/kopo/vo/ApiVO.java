package kr.ac.kopo.vo;

public class ApiVO {
	private String name;
	private int counter;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	@Override
	public String toString() {
		return "ApiVO [name=" + name + ", counter=" + counter + "]";
	}
	
}
