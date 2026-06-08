package com.mbc.addr.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.mbc.addr.dto.AddressBookDTO;
import com.mbc.addr.file.FileProc;

public class AddressBookDAO implements AddressBookInterface{
	// 인터페이스를 연결합니다....
	
	// 스캐너랑 파일 프로세서(가내수공업)을 임포트합니다...
	Scanner sc = new Scanner(System.in);
	FileProc fp;
	
	// 리스트를 만들어줍니다
	private List<AddressBookDTO> list;
	
	// DAO를 불러오면~ 파일 프로세서로 파일을 불러오게 해주고
	public AddressBookDAO() {
		fp = new FileProc("addressBook");
		load();	
	}
	
	
	// search 이거 내수용 함수를 만듭니다
	// 찾아서 리스트로 반환
	private List<AddressBookDTO> search(String str) {
		// 찾은 거 담을 리스트를 만들어두고
		List<AddressBookDTO> findList = new ArrayList<AddressBookDTO>();
		// 입력 받은 문자열을
		for(int i = 0;i<list.size();i++) {
			// 객체의 이름을 얻어와서 비교를 해여
			if(str.equals(list.get(i).getName())) {
				// 만약에 똑같으면 리스트에 저장합니다
				findList.add(list.get(i));
			}
		}
		return findList;
	}
	
	// 통합검색툴
	// 입력받기 싫어서 매개변수로 넘겨받기로 햇슨
	private void searchAll(String str) {
		// 요소별로 담을 리스트를 만들어줍니다
		List<AddressBookDTO> nameList = new ArrayList<AddressBookDTO>();
		List<AddressBookDTO> ageList = new ArrayList<AddressBookDTO>();
		List<AddressBookDTO> phNumList = new ArrayList<AddressBookDTO>();
		List<AddressBookDTO> addressList = new ArrayList<AddressBookDTO>();
		List<AddressBookDTO> memoList = new ArrayList<AddressBookDTO>();
		for(AddressBookDTO d : list) {
			// 각자에서 뭔가 포함하고 있으면 다 찾아서 각자 리스트에 담아주는 for문을 돌립니다...
			if(d.getName().contains(str)) nameList.add(d);
			if((d.getAge()+"").contains(str)) ageList.add(d);
			if(d.getPhoneNum().contains(str)) phNumList.add(d);
			if(d.getAddress().contains(str)) addressList.add(d);
			if(d.getMemo().contains(str)) memoList.add(d);
		}
		
		// 각자 출력
		System.out.println("=== \""+str+"\" 통합 검색 결과 ===");
		if(nameList.size()>0) {
			System.out.println("이름에서 일치하는 결과");
			for(AddressBookDTO d:nameList) System.out.println(d);
		}
		if(ageList.size()>0) {
			System.out.println("나이에서 일치하는 결과");
			for(AddressBookDTO d:ageList) System.out.println(d);
		}
		if(phNumList.size()>0) {
			System.out.println("전화번호에서 일치하는 결과");
			for(AddressBookDTO d:phNumList) System.out.println(d);
		}
		if(addressList.size()>0) {
			System.out.println("주소에서 일치하는 결과");
			for(AddressBookDTO d:addressList) System.out.println(d);
		}
		if(memoList.size()>0) {
			System.out.println("메모에서 일치하는 결과");
			for(AddressBookDTO d:memoList) System.out.println(d);
		}
	}
	
	// 마찬가지로 내수용 메소드
	// 숫자인지 아닌지 검사를 해가지고 만약에 아니면 다시 입력하라고 빠꾸놓는 놈이에여
	// 입력까지 한큐에 함
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
	
	@Override
	// CRUD에서 C
	// 자 더하자더하자
	public void insert() {
		// 넣을 거를 하나하나 받읍니다
		System.out.println("주소록 추가 >> ");
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("나이: ");
		int age = safe();
		System.out.print("전화번호: ");
		sc.nextLine();
		String phNum = sc.nextLine();
		System.out.print("주소: ");
		String address = sc.nextLine();
		System.out.print("메모: ");
		String memo = sc.nextLine();
		// 객체 생성해서 값 넣고 리스트에 넣읍니다 
		list.add(new AddressBookDTO(name, age, phNum, address, memo));
	}

	@Override
	// 삭제. CRUD의 D
	public void delete() {
		System.out.println("주소록 삭제 >> ");
		System.out.print("삭제할 정보의 이름을 입력하세요: ");
		// 삭제할 놈 핑을 찍어주쇼~ 하면 리스트에 담아줌
		String str = sc.next();
		List<AddressBookDTO> find = search(str);
		// 리스트 크기가 0이면 뭐 없는거죠. 끗
		if(find.size()==0) {
			System.out.println("일치하는 주소록 정보가 없습니다.");
			return;
		}
		// 1이면 짜피 하나니까 1나 없애버려
		else if(find.size()==1) {
			AddressBookDTO target = find.get(0);
			System.out.println(target+"을 삭제하였습니다.");
			list.remove(target);
		}
		// 0도 아니고 1도 아니면 이제 여러개란 소리니까
		else {
			System.out.println("중복된 정보가 존재합니다");
			// 중복건 출력 쫙 해주고
			for(int i=0;i<find.size();i++) {
				System.out.println(i+1+". "+find.get(i));
			}
			System.out.println(find.size()+1+". 취소");
			// 번호 입력 받아서 핑 찍고
			System.out.println("삭제할 정보의 번호를 입력해주세요.");
			int stu = safe();
			// 객체를 던져서 지워 
			// 근데 엉뚱 숫자 넣을 수도 있으닉가 조심조심 검사를 좀 하고
			if(stu>0&&stu<=find.size()) {
				AddressBookDTO target = find.get(stu-1);
				System.out.println(target+"을 삭제하였습니다.");
				list.remove(target);
			}
			else {
				System.out.println("삭제 프로그램을 취소합니다.");
			}
		}
		System.out.println("주소록 삭제 종료");
		System.out.println();
	}

	@Override
	// CRUD의 R
	public void select() {
		// 검색합니다. 이름으로.
		System.out.println("주소록 검색 >> ");
		System.out.print("검색할 정보의 이름을 입력하세요: ");
		String str = sc.next();
		// 검색해줭~ 하면 해줌. 리스트 줌.
		List<AddressBookDTO> find = search(str);
		
		if(find.size()==0) {
			System.out.println("일치하는 주소록 정보가 없습니다.");
		}
		for(AddressBookDTO dto : find) {
			System.out.println(dto);
		}
		System.out.println("주소록 검색 종료");
		System.out.println();
	}

	@Override
	// 주소 , 메모
	public void select2() {
		System.out.println("주소록 검색(주소, 메모) >> ");
		System.out.println("1. 주소 | 2. 메모");
		System.out.print(">>> ");
		// 주소 메모 양자택일해!! 근데 이제 number에 숫자 아닌 걸 쓸지도 몰르니까 safe 이용
		int number = safe();
		
		// 터지지 말라고 if문으로 조건 걸기
		if(number>2||number<1) {
			System.out.println("1, 2 중에서 입력해주세요");
			return;
		}
		// 리스트 만들어주고
		List<AddressBookDTO> findList = new ArrayList<AddressBookDTO>();
		sc.nextLine();
		System.out.print("검색어를 입력하세요~: ");
		String str = sc.nextLine();
		// 여기까진 공통으로 하고
		
		switch(number) {
		// number 따라서 getAddress 하느냐 getMemo 하느냐...
		case 1:
			for(int i = 0;i<list.size();i++) {
				if(str.equals(list.get(i).getAddress())) {
					findList.add(list.get(i));
				}
			}
			break;
		case 2:
			for(int i = 0;i<list.size();i++) {
				if(str.equals(list.get(i).getMemo())) {
					findList.add(list.get(i));
				}
			}
			break;
		default:
			break;
		}
		System.out.println("=== \""+str+"\" 검색 결과 ===");
		for(AddressBookDTO d:findList) System.out.println(d);
		System.out.println("주소록 검색 종료");
		System.out.println();
	}
	
	// 아까 종합 검색 만든 거 고대로 씁니다
	public void select3() {
		System.out.println("주소록 검색 >> ");
		System.out.print("검색할 정보를 입력하세요: ");
		sc.nextLine();
		String str = sc.nextLine();
		searchAll(str);
	}

	@Override
	// CRUD의 U
	public void update() {
		// 타겟 집어넣을 방을 미리 찍어두고
		AddressBookDTO target = null;
		System.out.println("주소록 수정 >> ");
		System.out.print("수정할 정보의 이름을 입력하세요: ");
		String str = sc.next();
		// 찾아주쇼~
		List<AddressBookDTO> find = search(str);
		if(find.size()==0) {
			System.out.println("일치하는 정보가 없습니다.");
			return;
		}
		else if(find.size()==1) {
			target = find.get(0);
			System.out.println(target);
		}
		else {
			System.out.println("중복된 주소록 정보가 존재합니다");
			for(int i=0;i<find.size();i++) {
				System.out.println(i+1+". "+find.get(i));
			}
			System.out.println(find.size()+1+". 취소");
			System.out.println("어떤 주소록 정보를 수정하시겠습니까?: ");
			int stu = safe();
			// 최종 타겟 확정
			if(stu>0&&stu<=find.size()) {
				target = find.get(stu-1);
			}
			else {
				System.out.println("수정 프로그램을 취소합니다.");
				return;
			}
		}
		System.out.println("수정하고 싶은 카테고리의 번호를 입력해주세요.");
		String[] arr = {"이름","나이","전화번호","주소","메모"};
		System.out.print("1. 이름 | 2. 나이 | 3. 전화번호 | 4. 주소 | 5. 메모 | 6. 취소");
		int ans = safe(); // 숫자 입력하라고 고나리하기
		// 입력 범위 고나리
		if(ans>5||ans<1) {
			System.out.println("수정 프로그램을 취소합니다.");
			return;
		}
		System.out.println(arr[ans-1]+" 수정을 시작합니다.");
		System.out.print("정보를 입력해주세요: ");
		sc.nextLine();
		switch(ans) {
		case 1:
			target.setName(sc.next());
			break;
		case 2:
			target.setAge(safe());
			break;
		case 3:
			target.setPhoneNum(sc.nextLine());
			break;
		case 4:
			target.setAddress(sc.nextLine());
			break;
		case 5:
			target.setMemo(sc.nextLine());
			break;
		default:
			System.out.println("수정 프로그램을 취소합니다.");
			break;
		}
		
	}

	@Override
	public void allprint() {
		if(list.size()==0) {
			System.out.println("현재 등록된 주소록이 없습니다.");
			return;
		}
		for(AddressBookDTO d : list) {
			System.out.println(d);
		}
	}

	@Override
	public void save() {
		// token 삽입을 toString으로 해결보기... 오오오... 
		List<String> strlist = new ArrayList<String>();
		
		for(int i=0;i<list.size();i++) {
			strlist.add(list.get(i).write());
		}
		fp.save(strlist);
	}
	
	@Override
	public void load() {
		list = new ArrayList<AddressBookDTO>();
		
		List<String> strList = fp.load();
		for(int i=0;i<strList.size();i++) {
			String[] split = strList.get(i).split("/",-1);
			if(split.length!=5) {
				System.out.println("데이터를 읽을 수 없습니다.");
				continue;
			}
			AddressBookDTO dto = new AddressBookDTO(split[0], // name
					Integer.parseInt(split[1]), // age
					split[2], // phone number
					split[3], // address
					split[4]); // memo
			
			list.add(dto);
		}
	}
}
