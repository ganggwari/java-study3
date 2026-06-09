package com.mbc.acc.main;

import java.util.Scanner;

import com.mbc.acc.dao.AccountDao;


public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountDao dao = new AccountDao();
		
		out: while(true) { // 내내 돌리기
			System.out.println("==================가계부 프로그램==================");
			System.out.println("1. 가계부 추가");
			System.out.println("2. 가계부 삭제");
			System.out.println("3. 가계부 검색");
			System.out.println("4. 가계부 수정");
			System.out.println("5. 가계부 출력");
			System.out.println("6. 월별 정산");
			System.out.println("7. 가계부 저장");
			System.out.println("8. 시스템 종료");
			System.out.println("____________________________________________________");
			System.out.print("사용할 메뉴 >> ");
			int work = sc.nextInt(); // 메뉴 입력을 받는다.
			System.out.println("____________________________________________________");
			
			switch(work) {
			// 1. 추가
			case 1: // 1을 입력받으면 이런 걸 실행
				dao.insert();
				break;
			// 2. 삭제
			case 2:
				dao.delete();
				break;
			// 3. 검색
			case 3:
				dao.select();
				break;
			// 4. 수정
			case 4:
				dao.update();
				break;
			case 5:
				dao.print();
				break;
			// 5. 출력
			case 6:
				dao.month();
				break;
			// 6. 결산		
			case 7:
				dao.save();
				break;
			// 7. 데이터 저장 -> 파일에 저장
			default: // 그외: 종료
				System.out.println("프로그램을 종료합니다...");
				break out;
			}
		}
		sc.close();
	}

}
