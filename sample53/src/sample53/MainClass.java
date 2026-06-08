package sample53;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List; // List는 interface다

public class MainClass {
	public static void main(String[] args) {
		/*
			Array List : 검색, 대입(배열)
						 O-O-O- ... 
			
			Linked List : 실시간 추가/삭제에 적합 -> 게임
			
			상호간 데이터를 던지고 받기가 가능하당
		
		*/
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("Giants");
		list.add(new String("Tigers"));
		list.add("Twins");
		//  -> 링.리로 만들어도 되고 걍 리스트로 해도 되고... 
		/* Linked */LinkedList<String> linkedList = new LinkedList<String>(list);
													// 이렇게 데이터 던져주기가 가능
		// List instance가 아니라 자식 class인 linked list, array list로 생성해도 서로 던져주기가 가능하다
		// ArrayList의 CRUD를 같이 사용한다.
		linkedList.add("Landers");
		for (String string : linkedList) {
			System.out.println(string);
		}
	
		// Linked List에만 있는 기능
		// addFirst: 0번지에 추가 / addLast: 마지막 번지에 추가
		linkedList.addFirst("Eagles");
		linkedList.addLast("Bears");
		
		for (String string : linkedList) {
			System.out.println(string);
		}
		// ArrayList에는 이런 거 없음ㅎㅎ
		// 기존 List 설명이 LinkedList의 설명이고 ArrayList는 주소값을 다 바까주고 해가지고 중간에 끼워넣고 하는 게 좀 느리다
		
		if(list.isEmpty()) {
			// 리스트가 비어있는지 아닌지 판별하는 불린 리턴 함수
			// ArrayList랑 LinkedList 둘 다 있다
		}
		
		/*
			iterator: 반복자 == pointer(주소) -> heap영역 주소(예)76f231 f == 1111 == 15
			
			list.get(index) 일케 꺼냈었다
			[0] -> [1] -> ... index number
			0x01   0x02		  iterator
		*/
		for (String string : linkedList) {
			System.out.println(string);
		}
		
		for (int i=0; i<linkedList.size();i++) {
			String str = linkedList.get(i);
			System.out.println(str);
		}
		
		Iterator<String> iter = linkedList.iterator(); // 제일 첫번째 주소를 iter에 대입
		while(iter.hasNext()) { // hasNext -> boolean 리턴
			String value = iter.next(); // next -> value값 리턴
			System.out.println("value: "+value);
		}
		
		
	}
}
