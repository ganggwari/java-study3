package sample54;

import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainClass {
	public static void main(String[] args) {
		/*
			collection
			
			Map : interface
			
			HashMap : class
					  사전
					  key:value 한 쌍으로 관리
					  -> index로 접근하지 않는다
					  
					   key	    value -> web에서 Json... 뭐 이런데서 쓴다
					  "apple": "사과"  -> one pair
					  Tree 구조로 구성되어 있음
					  Key 값은 중복을 허용하지 않는다
			TreeMap : class
					  HashMap + Sorting(key값을)
					  HashMap에 비해 무겁다(효율이 떨어짐)
		*/
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map<String, String> hMap = new HashMap<String, String>(); // 이렇게 더 자주 쓴다
		
		// 추가 C
		hMap.put("apple", "사과");
		hMap.put("pear", "배");
		hMap.put("banana", "바나나");
		
		// map의 크기
		System.out.println(hMap.size());
		
		// key로 value를 취득
		String _value = hMap.get("banana");
		System.out.println(_value);
		
		// 다 출력
		// 꼭 key 값의 자료형을 따라간다
		Iterator<String> iter = hMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = hMap.get(key);
			
			System.out.println("key: "+key+" value: "+value);
		}
		
		// 삭제 D
		_value = hMap.remove("pear"); // 삭제된 값 리턴
		System.out.println("삭제된 데이터는 "+_value+"입니다.");
		
		// 있는 키값을 중복해서 넣으면 수정이 되어버림
		// 원본 데이터가 날아가는 참사가...!!
		hMap.put("apple", "능금");
		
		iter = hMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = hMap.get(key);
			
			System.out.println("key: "+key+" value: "+value);
		}
		
		// 검색 R
		boolean b = hMap.containsKey("apple");
		System.out.println(b);
		
		if(hMap.containsKey("banana")) {
			String value = hMap.get("banana"); 
			System.out.println(value);
		}
		
		// 수정 U
		_value = hMap.replace("apple", "사과"); // 수정 이전 값 리턴
		System.out.println("변경 전 값: "+_value);
		
		_value = hMap.get("apple");
		System.out.println("변경 후 값: "+_value);
		
		
		hMap.put("grape", "포도");
		hMap.put("pear", "배");
		hMap.put("orange", "오렌지");
		
		iter = hMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = hMap.get(key);
			
			System.out.println("key: "+key+" value: "+value);
		}
		System.out.println();
		
		// TreeMap을 이용한 정렬
		// HashMap -> TreeMap 전환 가능
		TreeMap<String, String> tMap = new TreeMap<String, String>(hMap);
		
		// 오름차순(key 기준)
		Iterator<String> it = tMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = tMap.get(key);
			
			System.out.println("key: "+key+" value: "+value);
		}		
		System.out.println();
		
		// 내림차순(key 기준)
		Iterator<String> ite = tMap.descendingKeySet().iterator();
		
		while(ite.hasNext()) {
			String key = ite.next();
			String value = tMap.get(key);
			
			System.out.println("key: "+key+" value: "+value);
		}
		
		// 제일 많이많이 쓰는 유형의 HashMap
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		List<String> list = new ArrayList<String>();
		list.add("메모");
		list.add("내용입니다.");
		
		// String, List, Integer, StudentDTO, AddressDTO
		dataMap.put("학생", "홍길동");
		dataMap.put("번호", 1001);
		dataMap.put("내용	", list);
	}
}
