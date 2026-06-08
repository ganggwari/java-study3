package com.mbc.addr.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.mbc.addr.dto.AddressBookDTO_T;
import com.mbc.addr.file.FileProc_T;
import com.mbc.addr.singleton.SingletonClass;

public class AddressBookDAO_T implements AddressBookInterface_T{
	// 인터페이스를 연결합니다....
	
	// 스캐너랑 파일 프로세서(가내수공업)을 임포트합니다...
	Scanner sc = new Scanner(System.in);
	
	// 리스트를 만들어줍니다
//	private List<AddressBookDTO_T> list = new ArrayList<AddressBookDTO_T>();
	
	private FileProc_T fproc;
	
	
	
	// DAO를 불러오면~ 파일 프로세서로 파일을 불러오게 해주고
	public AddressBookDAO_T() {
		fproc = new FileProc_T("Address");
		fproc.load();
//		list.add(new AddressBookDTO_T("홍길동", 12, "123", "부평시", "대학친구"));
//		list.add(new AddressBookDTO_T("홍길동", 26, "789", "부산시", "사촌형"));
//		list.add(new AddressBookDTO_T("성춘향", 24, "456", "인천시", "헬스"));
//		list.add(new AddressBookDTO_T("일지매", 36, "783", "고양시", "교회친구"));
	}
	

	@Override
	// CRUD에서 C
	// 자 더하자더하자
	public void insert() {
		// 넣을 거를 하나하나 받읍니다
		System.out.println("데이터 추가입니다 >> ");
		
		AddressBookDTO_T dto = new AddressBookDTO_T();
		System.out.print("이름: ");
		dto.setName(sc.next());
		System.out.print("나이: ");
		dto.setAge(sc.nextInt());
		System.out.print("전화번호: ");
		dto.setPhoneNum(sc.next());
		System.out.print("주소: ");
		dto.setAddress(sc.next());
		System.out.print("메모: ");
		dto.setMemo(sc.next());
		// 객체 생성해서 값 넣고 리스트에 넣읍니다 
		SingletonClass single = SingletonClass.getInstance();
		boolean isS = single.list.add(dto);
		if(!isS) {
			System.out.println("추가되지 않았씁니다~");
		}
		else {
			System.out.println("추가되었읍니다~");
		}
		
	}

	@Override
	public int search(String name) {
		SingletonClass single = SingletonClass.getInstance();
		int index = -1;
		for(int i=0; i<single.list.size();i++) {
			AddressBookDTO_T dto = single.list.get(i);
			
			if(name.equals(dto.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	@Override
	// 삭제. CRUD의 D
	public void delete() {
		System.out.println("데이터 삭제 >> ");
		System.out.print("이름: ");
		String name = sc.next();
		// 웬만하며ㅑㄴ 입력은 여기서 받는 게 좋다
		int index = search(name);
		
		if (index==-1) {
			System.out.println("데이터를 찾을 수 없습니다");
		}
		SingletonClass single = SingletonClass.getInstance();
		AddressBookDTO_T deletedDTO = single.list.remove(index);
		deletedDTO.info();
		System.out.println("데이터를 삭제하였습니다."); 
		
		
	}

	@Override
	// CRUD의 R
	public void selectName() {
		// 검색합니다. 이름으로.
		System.out.println("이름 검색입니다");
		System.out.print("이름: ");
		String name = sc.next();
		// 검색해줭~ 하면 해줌. 리스트 줌.
		List<AddressBookDTO_T> findList = new ArrayList<AddressBookDTO_T>();
		SingletonClass single = SingletonClass.getInstance();
		for(AddressBookDTO_T dto : single.list) {
			if(name.equals(dto.getName())) {
				findList.add(dto);
			}
		}
		
		if (findList.size()==0) {
			System.out.println("데이터를 찾을 수 없습니다.");
		}
		for (AddressBookDTO_T d : findList) {
			d.info();
		}
		System.out.println("주소록 검색 종료");
		System.out.println();
	}

	@Override
	// 주소 , 메모
	public void selectMemo() {
		System.out.println("메모 검색입니다");
		System.out.print("메모: ");
		String memo = sc.next();
		
		List<AddressBookDTO_T> findList = new ArrayList<AddressBookDTO_T>();
		SingletonClass single = SingletonClass.getInstance();
		for(AddressBookDTO_T dto:single.list) {
			if(dto.getMemo().contains(memo)) {
				findList.add(dto);
			}
		}
		if (findList.size()==0) {
			System.out.println("데이터를 찾을 수 없습니다.");
		}
		for (AddressBookDTO_T d : findList) {
			d.info();
		}
	}

	@Override
	// CRUD의 U
	public void update() {
		System.out.println("데이터 삭제 >> ");
		System.out.print("이름: ");
		String name = sc.next();
		// 웬만하며ㅑㄴ 입력은 여기서 받는 게 좋다
		int index = search(name);
		
		if (index==-1) {
			System.out.println("데이터를 찾을 수 없습니다");
		}
		
		System.out.println("수정할 주소 입력: ");
		String address = sc.next();
		SingletonClass single = SingletonClass.getInstance();
	//	list.get(index).setAddress(address);
		AddressBookDTO_T updateDto = single.list.get(index);
		updateDto.setAddress(address);
		
	}

	@Override
	public void allprint() {
		SingletonClass single = SingletonClass.getInstance();
		for(AddressBookDTO_T d : single.list) {
			d.info();
		}
	}


	@Override
	public void fileSave() {
		// token 삽입을 toString으로 해결보기... 오오오... 
//		String[] arr = new String[list.size()];
//		
//		for(int i=0;i<list.size();i++) {
//			arr[i] = list.get(i).toString();
//		}
//		System.out.println(Arrays.toString(arr));
//		
//		fproc.save(arr);
		fproc.save();
	}

/*
	@Override
	public void fileLoad() {
		list = new ArrayList<AddressBookDTO_T>();
		
		String arr[] = fproc.load();
		for (int i = 0; i < arr.length; i++) {
			String split[] = arr[i].split("/");
			
			AddressBookDTO_T dto = new AddressBookDTO_T(split[0], // name
														Integer.parseInt(split[1]), // age
														split[2], // phone number
														split[3], // address
														split[4]); // memo
			list.add(dto);
		}
	}
*/	
	
	
	
	
	
}
