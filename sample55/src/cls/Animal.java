package cls;

import single.SingletonClass;

public class Animal {
	private int number;
	private String name;

	public Animal(int number) {
		this.number = number;
	}
	
//	public void setName(String name) {
//		this.name = name;
//	}
	
	public void info() {
		SingletonClass sc = SingletonClass.getInstance();
		this.name = sc.name;
		System.out.println("name: "+name);
	}
	
	
}
