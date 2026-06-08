package com.mbc.addr.main;

import java.util.Scanner;

import com.mbc.addr.dao.AddressBookDAO_T;

public class MainClass_T {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBookDAO_T dao = new AddressBookDAO_T();
		
		out: while(true) { // 내내 돌리기
			System.out.println("==================주소록 관리 프로그램==================");
			System.out.println("1. 데이터 추가");
			System.out.println("2. 데이터 삭제");
			
			System.out.println("3. 데이터 검색(이름)");
			System.out.println("4. 데이터 검색(주소, 메모)");
			
			System.out.println("5. 데이터 수정");
			System.out.println("6. 데이터 출력");
			System.out.println("7. 데이터 저장");
			System.out.println("____________________________________________________");
			System.out.print("메뉴 번호 입력 >> ");
			int work = sc.nextInt(); // 메뉴 입력을 받는다.
			
			switch(work) {
			// 1. 추가
			case 1: // 1을 입력받으면 이런 걸 실행
				dao.insert();
				break;
			// 2. 삭제
			case 2:
				dao.delete();
				break;
			// 3. 검색(이름)
			case 3:
				dao.selectName();
				break;
			// 4. 검색(주소, 메모) 이름이랑 따로 분리하든가 아님 한방에 하든가...
			case 4:
				dao.selectMemo();
				break;
			// 5. 수정
			case 5:
				dao.update();
				break;
			// 6. 출력		
			case 6:
				dao.allprint();
				break;
			// 7. 데이터 저장 -> 파일에 저장
			case 7:
				dao.fileSave();
				break;
			default: // 안 고르면 종료
				System.out.println("프로그램을 종료합니다...");
				break out;
			}
		}
		
	
	}
}
