package sample55;

import cls.Animal;
import cls.Human;
import single.SingletonClass;

public class MainClass {
	public static void main(String[] args) {
		
		Human human = new Human("성춘향");
		Animal animal = new Animal(3);
		
		
		// 개번거롭게 getter setter로 대화시키기
//		String name = human.getName();
//		animal.setName(name);
		animal.info();
		
		// singleton 클래스는 생성 불가능하게 만들어놓는다
		// SingletonClass sc = new SingletonClass();
		/*
		SingletonClass sc = SingletonClass.getInstance();
		System.out.println(sc);
		
		// getInstance 함수에 의해 SingletonClass라는 객체는 단 한 번만 생성된다.
		sc = SingletonClass.getInstance();
		System.out.println(sc);
		*/
	}
}
