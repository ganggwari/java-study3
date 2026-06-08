package com.mbc.addr.main;

import java.util.Scanner;

import com.mbc.addr.dao.AddressBookDao;
import com.mbc.addr.dao.AddressBookDaoImpl;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		AddressBookDao dao = new AddressBookDaoImpl();

		while(true) {
			// menu
			System.out.println("<<< 주소록 >>>");
			System.out.println("1.데이터 추가");
			System.out.println("2.데이터 삭제");
			
			System.out.println("3.데이터 이름 검색");
			System.out.println("4.데이터 메모 검색");
			
			System.out.println("5.데이터 수정");
			System.out.println("6.모두 출력");
			System.out.println("7.데이터 저장");
			
			System.out.print("번호 입력 >> ");
			int work = sc.nextInt();
			
			switch(work) {
				// 1.추가
				case 1:
					dao.insert();
					break;
				// 2.삭제
				case 2:
					dao.delete();
					break;
				// 3.검색 - 이름
				case 3:
					dao.selectName();
					break;
				// 4.검색 - 주소 or 메모
				case 4:
					dao.selectMemo();
					break;
				// 5.수정
				case 5:
					dao.update();
					break;
				// 6.모두출력
				case 6:
					dao.allprint();
					break;
				// 7.데이터 저장
				case 7:
					dao.fileSave();
					break;
			}		
		}
	}
}
