package com.mbc.acc.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.mbc.acc.dto.AccountDto;
import com.mbc.acc.file.FileProc;
import com.mbc.acc.single.AccountSingleton;

public class AccountDao implements DaoInterface{
	Scanner sc = new Scanner(System.in);
	
	// 생성자에 파일 로딩 넣기
	public AccountDao() {
		// 리스트 접근
		AccountSingleton as = AccountSingleton.getInstance();
		
		// 받을 문자열 리스트 만들기
		List<String> strList = new ArrayList<String>();
		// 파일프록으로 받아오기 (파일 이름, 넣어줄 리스트)
		FileProc.load("accountbook", strList);
		// 하나씩 꺼내서 쪼개기
		for(String str : strList) {
			// 끝칸 만들기
			String[] arr = str.split("/", -1);
			// 5칸 아니면 뱉기
			if(arr.length!=5){
				System.out.println("데이터를 읽어올 수 없습니다.");
				continue;
			}
			// dto 객체에 넣기
			AccountDto dto = new AccountDto(arr[0], Integer.parseInt(arr[1]), arr[2], arr[3], Integer.parseInt(arr[4]));
			// 리스트에 dto 넣기
			as.list.add(dto);
		}
	}
	
	// 내부 메소드 search -> 일치하는 건 다 가져오기
	private List<AccountDto> search(String str) {
		// list 접근하고
		AccountSingleton as = AccountSingleton.getInstance();
		// 찾은 거 넣을 리스트 만들기
		List<AccountDto> findlist = new ArrayList<AccountDto>();
		// 하나씩 꺼내서
		for(AccountDto d : as.list) {
			// 정수 데이터에서도 검색하고 싶은 게 있을 수 있으니까 문자열로 바꾸고
			String moneyStr = d.getMoney()+"";
			String dateStr = d.getAdate()+"";
			// str 포함하면 한 번만 findlist에 넣는다
			if(d.getIoKind().contains(str)||
					d.getTitle().contains(str)||
					d.getContent().contains(str)||
					moneyStr.contains(str)||
					dateStr.contains(str)) {
				findlist.add(d);
			}
		}
		return findlist;
	}
	
	// 숫자 제대로 입력했는지 아닌지... 
	private int safe() {
		while(true) {
			try {
				String str = sc.next();
				return Integer.parseInt(str);
			}catch(Exception e) {
				System.out.println("숫자로 입력해주세요.");
				System.out.print(">>> ");
			}
			
		}
	}
	
	// 입력. 수입/지출 + 액수 + 제목 + 내용 + 날짜
	@Override
	public void insert() {
		System.out.println("가계부 작성을 시작합니다!!");
		System.out.println("____________________________________________________");
		loop:while(true) {
			System.out.println("수입/지출을 선택해주세요.");
			System.out.print("수입(1)/지출(2): ");
			// 오타 나서 검색 삐꾸나느니 숫자로 받아서 넣는 게 낫다
			String ioKind;
			while(true) {
				int num = safe();
				if(num==1) {
					ioKind = "수입";
				}
				else if(num==2) {
					ioKind = "지출";
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
				break;
			}
			System.out.print("액수를 입력해주세요: ");
			int money = safe();
			System.out.print("제목을 입력해주세요: ");
			sc.nextLine();
			String title = sc.nextLine();
			System.out.println("내용을 입력해주세요: ");
			String content = sc.nextLine();
			System.out.println("날짜를 입력해주세요 예) 260609 : ");
			int adate = safe();
			// 리스트 불러오기
			AccountSingleton as = AccountSingleton.getInstance();
			// 추가하고 불린 받아서
			boolean b = as.list.add(new AccountDto(ioKind, money, title, content, adate));
			System.out.println("____________________________________________________");
			// 정상 처리 여부 출력
			if(b) {
				System.out.println("정상적으로 추가되었습니다.");
			}
			else {
				System.out.println("추가되지 않았습니다.");
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 작성을 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					break;
				}
				else if(num==2) {
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
				break loop;
			}
		}
	}
	
	// 삭제. 검색어 돌려서 해당하는 거 다 나오게 하고, 삭제 전에 확인 1회
	@Override
	public void delete() {
		System.out.println("가계부 삭제를 시작합니다!!");
		System.out.println("____________________________________________________");
		loop: while(true) {
			System.out.println("삭제를 원하는 정보를 검색합니다.");
			System.out.print("수입/지출, 금액, 메모 제목, 내용, 날짜로 검색해주세요: ");
			sc.nextLine();
			// 검색어 받고
			String ans = sc.nextLine();
			// 찾아서 리스트 받고
			List<AccountDto> find = search(ans);
			// 지워줄 객체 타겟 미리 만들어놓고
			AccountDto target = null;
			System.out.println("____________________________________________________");
			// 리스트 크기 0이면 없는 거
			if(find.size()==0) {
				System.out.println("일치하는 정보가 없습니다. 삭제 메뉴로 돌아갑니다.");
				continue;
			}
			// 1이면 어차피 하나
			else if(find.size()==1) {
				target = find.get(0);
			}
			// 여러개면 선택지 제공
			else {
				for (int i=0; i<find.size();i++) {
					System.out.print(i+1+". "); // 1. 지출 : ~~~ 
					find.get(i).info();
				}
				System.out.println(find.size()+1+". 취소");
				System.out.print("몇 번째 정보를 삭제하시겠습니까?: ");
				int num = safe()-1;
				if(num>-1&&num<find.size()) {
					target = find.get(num);
				}
				else {
					// 메인메뉴로 돌아가기
					System.out.println("취소되었습니다.");
					break;
				}
			}
			System.out.println("삭제할 가계부가 맞는지 확인해주세요");
			target.info();
			System.out.println("____________________________________________________");
			System.out.print("삭제하려면 1, 취소하려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					AccountSingleton as = AccountSingleton.getInstance();
					if(as.list.remove(target)) {
						System.out.println("성공적으로 삭제되었습니다.");
					}
					break;
				}
				else if(num==2) {
					System.out.println("취소되었습니다.");
					break;
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
				}
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 삭제를 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					break;
				}
				else if(num==2) {
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
				break loop;
			}
		}
	}
	// 검색.
	@Override
	public void select() {
		System.out.println("가계부 검색을 시작합니다!!");
		System.out.println("____________________________________________________");
		loop: while(true) {
			System.out.println("검색어를 입력해주세요: ");
			sc.nextLine();
			String str = sc.nextLine();
			List<AccountDto> find = search(str);
			if(find.size()==0) {
				System.out.println("일치하는 검색 결과를 찾을 수 없습니다.");
			}
			else{
				for(AccountDto d:find) {
				d.info();
				}
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 검색을 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					break;
				}
				else if(num==2) {
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
				break loop;
			}
		}
	}
	
	// 수정. 원하는 카테고리 수정하게 해야지...
	@Override
	public void update() {
		System.out.println("가계부 수정을 시작합니다!!");
		System.out.println("____________________________________________________");
		loop: while(true) {
			System.out.println("수정을 원하는 정보를 검색합니다.");
			System.out.print("수입/지출, 금액, 메모 제목, 내용, 날짜로 검색해주세요: ");
			sc.nextLine();
			String ans = sc.nextLine();
			List<AccountDto> find = search(ans);
			AccountDto target = null;
			if(find.size()==0) {
				System.out.println("일치하는 정보가 없습니다. 수정 메뉴로 돌아갑니다.");
				continue;
			}
			else if(find.size()==1) {
				target = find.get(0);
			}
			else {
				for (int i=0; i<find.size();i++) {
					System.out.print(i+1+". ");
					find.get(i).info();
				}
				System.out.println(find.size()+1+". 취소");
				System.out.print("몇 번째 정보를 수정하시겠습니까?: ");
				int num = safe()-1;
				if(num>-1&&num<find.size()) {
					target = find.get(num);
				}
				else {
					break;
				}
			}
			System.out.println("수정할 가계부가 맞는지 확인해주세요");
			target.info();
			System.out.print("수정하려면 1, 취소하려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					System.out.println("수정을 원하는 정보를 선택해주세요.");
					System.out.println("1. 수입/지출 카테고리 | 2. 금액 | 3. 메모 제목 | 4. 메모 내용 | 5. 날짜 ");
					System.out.print(">>> ");
					int uans = safe();
					if(uans>0&&uans<6) {
						System.out.print("수정할 내용을 적어주세요: ");
						sc.nextLine();
						String str = sc.nextLine();
						System.out.println("____________________________________________________");
						switch(uans) {
						case 1:
							if(str.equals("수입")||str.equals("지출")) {
								target.setIoKind(str);
								System.out.println("수정 완료되었습니다.");
							}
							else {
								System.out.println("수입 혹은 지출로만 변경 가능한 카테고리입니다.");
							}
							break;
						case 2:
							try {
								int number = Integer.parseInt(str);
								target.setMoney(number);
								System.out.println("수정 완료되었습니다.");
							} catch (Exception e) {
								System.out.println("숫자로 입력해주세요.");
							}
							break;
						case 3:
							target.setTitle(str);
							System.out.println("수정 완료되었습니다.");
							break;
						case 4:
							target.setContent(str);
							System.out.println("수정 완료되었습니다.");
							break;
						case 5:
							try {
								int number = Integer.parseInt(str);
								target.setAdate(number);
								System.out.println("수정 완료되었습니다.");
							} catch (Exception e) {
								System.out.println("숫자로 입력해주세요.");
							}
							break;
						}
						break;
					}
					else {
						System.out.println("잘못된 번호를 입력하셨습니다.");
						break;
					}
				}
				else if(num==2){
					System.out.println("취소되었습니다.");
					break;
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
				}
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 수정을 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int num = safe();
				if(num==1) {
					break;
				}
				else if(num==2) {
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
				break loop;
			}
		}
	}
	
	// 출력. 전체 출력 메뉴/기간 지정 출력 메뉴
	@Override
	public void print() {
		System.out.println("가계부 출력을 시작합니다!!");
		System.out.println("____________________________________________________");
		loop: while(true) {
			System.out.println("어떻게 출력할까요?");
			System.out.print("1. 전체 출력 | 2. 기간 설정 출력 | 3. 메뉴로 돌아가기");
			int num = safe();
			if (num==1) {
				System.out.println("가계부를 전체 출력합니다!");
				AccountSingleton as = AccountSingleton.getInstance();
				System.out.println("____________________________________________________");
				for(AccountDto d:as.list) {
					d.info();
				}
				System.out.println("출력 완료!!");
			}
			else if(num==2) {
				System.out.println("출력할 기간을 설정합니다.");
				System.out.print("시작 시점을 설정해주세요(YYMMDD): ");
				int start = safe();
				System.out.print("종료 시점을 설정해주세요(YYMMDD): ");
				int end = safe();
				if (start>end) {
					int temp = start;
					start = end;
					end = temp;
				}
				List<AccountDto> pick = new ArrayList<AccountDto>();
				AccountSingleton as = AccountSingleton.getInstance();
				for(AccountDto d:as.list) {
					if(d.getAdate()>=start&&d.getAdate()<=end) {
						pick.add(d);
					}
				}
				if (pick.size()==0) {
					System.out.println("해당 기간에 작성된 가계부가 없습니다.");
					continue;
				}
				// 기간 정렬이 해보고 싶었음
				pick.sort(new Comparator<AccountDto>(){

					@Override
					public int compare(AccountDto o1, AccountDto o2) {
						return Integer.compare(o1.getAdate(), o2.getAdate());
					}
					
				});
				System.out.println("____________________________________________________");
				for (AccountDto d:pick) {
					d.info();
				}
				System.out.println("출력 완료!!");
			}
			else {
				System.out.println("메뉴로 돌아갑니다.");
				break loop;
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 출력을 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int numb = safe();
				if(numb==1) {
					break;
				}
				else if(numb==2) {
					break loop;
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
			}
		}
	}
	// 정산. 수입 지출 계산해서 적자 흑자 결산
	@Override
	public void month() {
		System.out.println("가계부 결산을 시작합니다!!");
		System.out.println("____________________________________________________");
		loop: while(true) {
			System.out.println("결산을 원하는 기간을 설정합니다.");
			System.out.print("시작 시점을 설정해주세요(YYMMDD): ");
			int start = safe();
			System.out.print("종료 시점을 설정해주세요(YYMMDD): ");
			int end = safe();
			if (start>end) {
				int temp = start;
				start = end;
				end = temp;
			}
			List<AccountDto> pick = new ArrayList<AccountDto>();
			AccountSingleton as = AccountSingleton.getInstance();
			for(AccountDto d:as.list) {
				if(d.getAdate()>=start&&d.getAdate()<=end) {
					pick.add(d);
				}
			}
			if (pick.size()==0) {
				System.out.println("해당 기간에 작성된 가계부가 없습니다. 출력 메뉴로 돌아갑니다.");
				continue;
			}
			int earn = 0;
			int spend = 0;
			for (AccountDto d:pick) {
				if(d.getIoKind().equals("수입")) {
					earn += d.getMoney();
				}
				else if (d.getIoKind().equals("지출")) {
					spend += d.getMoney();
				}
				else {
					System.out.println(d.getAdate()+"의 <" +d.getTitle()+"> 내역을 확인할 수 없습니다.");
				}
			}
			int sum = earn - spend;
			System.out.println("____________________________________________________");
			System.out.println(start + " - "+ end +" 의 결산은 수입 "+ earn +" 원, 지출 " + spend +" 원입니다.");
			if (sum<0) {
				System.out.println(sum+ " 원 적자입니다.");
			}
			else if(sum>0 ){
				System.out.println((Math.abs(sum))+" 원 흑자입니다.");
			}
			else {
				System.out.println(sum + " 원 입니다.");
			}
			System.out.println("____________________________________________________");
			System.out.println("가계부 결산을 계속 하시겠습니까?");
			System.out.print("계속하시려면 1, 메뉴로 돌아가려면 2를 눌러주세요: ");
			while(true) {
				int numb = safe();
				if(numb==1) {
					break;
				}
				else if(numb==2) {
					break loop;
				}
				else {
					System.out.println("1 혹은 2로 입력해주세요.");
					continue;
				}
			}
		}
	}
	// 정보 저장
	@Override
	public void save() {
		System.out.println("가계부를 저장합니다...");
		List<String> strList = new ArrayList<String>();
		AccountSingleton as = AccountSingleton.getInstance();
		for(AccountDto d : as.list) {
			strList.add(d.toString());
		}
		FileProc.save("accountbook", strList);
		System.out.println("저장 완료!!");
	}

}
