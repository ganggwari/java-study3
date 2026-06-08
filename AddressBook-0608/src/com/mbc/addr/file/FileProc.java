package com.mbc.addr.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.mbc.addr.dto.AddressBookDTO;

public class FileProc {
	// 파일 주소 적어넣기
	File file;
	
	public FileProc(String filename) {
		file = new File("C:/tmp/"+filename+".txt");
		
		try {
			if( file.createNewFile()) {
				System.out.println("파일 생성 성공");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 저장
	public void save(List<String> list) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))){		
			// 받은 list에서 dto로 객체 꺼내기
			for(String str : list) {
				pw.println(str); // 줄별로 파일에 출력하기
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 불러오기
	public List<String> load() { // 파일 리턴하는 함수
		List<String> list = new ArrayList<String>();
		// 새 리스트를 하나 만들어줍니당
		
		try (BufferedReader br = new BufferedReader(new FileReader(file));){
			
			// 데이터 입력받을 String 준비 -> 외냐? 한 사람 데이터가 한 줄로 들어가게 출력을 했으닉가... 한줄씩 빼옴
			String str = "";
			
			// str이 빈 거 아니면~
			while ((str = br.readLine())!=null) {
				list.add(str);
				
				
				// token으로 잘라잘라 배열로 받고(근데 메모가 빈 방이면 곤란해진다네여 그래서 -1을 붙이라는데 원리를 모르것네
//				String[] array = str.split("/",-1);
//				// 만약에 배열 길이가 5가 아니면 비정상 데이터니까 건너뛰기ㄱㄱ
//				if (array.length!=5) {
//					System.out.println("데이터를 읽을 수 없습니다.");
//					continue;
//				}
//				// 어차피 데이터 저장 순서는 다 통제를 해놨으니까 번지수로 꺼내도 무방하다
//				String name = array[0];
//				int age = Integer.parseInt(array[1]);
//				String num = array[2];
//				String address = array[3];
//				String memo = array[4];
//				
//				// 객체에 넣어주기
//				AddressBookDTO dto = new AddressBookDTO(name, age, num, address, memo);
//				// 생성한 객체를 리스트에 넣어주기
//				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		// 리스트 리턴
	}
	
}
