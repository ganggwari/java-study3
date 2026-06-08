package com.mbc.addr.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.mbc.addr.dto.AddressBookDto;
import com.mbc.addr.file.FileProc;
import com.mbc.addr.singleton.Singleton;

public class AddressBookDaoImpl implements AddressBookDao{
	
	Scanner sc = new Scanner(System.in);
	
//	private List<AddressBookDto> list = new ArrayList<AddressBookDto>();
//	private List<AddressBookDto> list;
	
	private FileProc fproc;

	// CRUD
	public AddressBookDaoImpl() {
		
		fproc = new FileProc("addressBook");
		
		fproc.load();
	//	fileLoad();
		
//		list.add(new AddressBookDto("홍길동", 24, "123", "부평시", "대학친구"));
//		list.add(new AddressBookDto("성춘향", 15, "234", "부천시", "후배"));
//		list.add(new AddressBookDto("일지매", 22, "345", "서울시", "교회친구"));	
//		list.add(new AddressBookDto("홍길동", 26, "456", "부산시", "사촌형"));
	}
	
	@Override
	public void insert() {
		// TODO 추가
		System.out.println("데이터 추가입니다");
		
		AddressBookDto dto = new AddressBookDto();
		
		System.out.print("이름 = ");
		dto.setName(sc.next());
		
		System.out.print("나이 = ");
		dto.setAge(sc.nextInt());
		
		System.out.print("전화번호 = ");
		dto.setPhone(sc.next());
		
		System.out.print("주소 = ");
		dto.setAddress(sc.next());
		
		System.out.print("메모 = ");
		dto.setMemo(sc.next());
		
		Singleton single = Singleton.getInstance();		
		boolean isS = single.list.add(dto);
		if(!isS) {
			System.out.println("추가되지 않았습니다");
		}
		
		System.out.println("정상적으로 추가되었습니다");
	}

	@Override
	public void delete() {
		// TODO 삭제
		System.out.println("데이터 삭제입니다");
		
		System.out.print("이름 = ");		
		String name = sc.next();
		
		int index = search(name);		
		if(index == -1) {
			System.out.println("데이터를 찾을 수 없습니다");
			return;
		}
		
		Singleton single = Singleton.getInstance();	
		AddressBookDto deleteDto = single.list.remove(index);		
		deleteDto.info();
		System.out.println("데이터를 삭제하였습니다");
	}

	@Override
	public void update() {
		// TODO 수정
		System.out.println("데이터 수정입니다");
		
		System.out.print("이름 = ");		
		String name = sc.next();
		
		int index = search(name);		
		if(index == -1) {
			System.out.println("데이터를 찾을 수 없습니다");
			return;
		}
		
		System.out.print("수정할 주소 입력 = ");
		String address = sc.next();
		
		Singleton single = Singleton.getInstance();	
		AddressBookDto updateDto = single.list.get(index);
		updateDto.setAddress(address);
		
		System.out.println("수정되었습니다");
	}

	@Override
	public void selectName() {
		// TODO 이름 검색
		System.out.println("이름 검색입니다");
		
		System.out.print("이름 = ");		
		String name = sc.next();
		
		List<AddressBookDto> findList = new ArrayList<AddressBookDto>();
		
		Singleton single = Singleton.getInstance();	
		for (AddressBookDto addr : single.list) {
			if(name.equals(addr.getName())) {
				findList.add(addr);
			}
		}
		
		if(findList.size() == 0) {
			System.out.println("데이터를 찾을 수 없습니다");
			return;
		}
		
		for (AddressBookDto addr : findList) {
			addr.info();		
		}
	}

	@Override
	public void selectMemo() {
		// TODO 메모 검색
		System.out.println("메모 검색입니다");
		
		System.out.print("메모 = ");		
		String memo = sc.next();
		
		List<AddressBookDto> findList = new ArrayList<AddressBookDto>();
		
		Singleton single = Singleton.getInstance();	
		for (AddressBookDto addr : single.list) {
			if(addr.getMemo().contains(memo)) {
				findList.add(addr);
			}			
		}
		
		if(findList.size() == 0) {
			System.out.println("데이터를 찾을 수 없습니다");
			return;
		}
		
		for (AddressBookDto addr : findList) {
			addr.info();
		}		
	}

	@Override
	public int search(String name) {
		// TODO 써치
		int index = -1;
		
		Singleton single = Singleton.getInstance();	
		for (int i = 0; i < single.list.size(); i++) {
			AddressBookDto dto = single.list.get(i);			
			if(name.equals(dto.getName())) {
				index = i;
				break;
			}
		}		
		
		return index;
	}

	@Override
	public void allprint() {
		Singleton single = Singleton.getInstance();	
		for (AddressBookDto addr : single.list) {
			addr.info();
		}				
	}

	@Override
	public void fileSave() {		
		// 홍길동-24-1234-부평구-친구
		/*
		String arr[] = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			AddressBookDto dto = list.get(i);
			arr[i] = dto.toString(); 
		}
		
		System.out.println(Arrays.toString(arr));		
		
		fproc.save(arr);*/	
		
		fproc.save();		
	}

	/*
	@Override
	public void fileLoad() {		
		//list = new ArrayList<AddressBookDto>();
		
		String arr[] = fproc.load();
		
		// arr[0] ->  홍길동-24-123-부평시-고교친구		
		//String str[] = arr[0].split("-");
					
		for (int i = 0; i < arr.length; i++) {
			
			String str[] = arr[i].split("-");			
			AddressBookDto dto = new AddressBookDto(str[0], // 이름
													Integer.parseInt(str[1]), // 나이 
													str[2], // 전화번호
													str[3], // 주소
													str[4]); // 메모
			list.add(dto);
		}		
	}	*/
}








