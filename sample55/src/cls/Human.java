package cls;

import single.SingletonClass;

public class Human {
	private String name;

	public Human(String name) {
		this.name = name;
		
		SingletonClass sc = SingletonClass.getInstance();
		sc.name = this.name;
	}
	
//	public String getName() {
//		return name;
//	}
	
	
}
