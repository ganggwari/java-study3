package com.mbc.acc.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileProc {
	
	static File file;
	
	private FileProc() {
	}
	
	public static void save(String filename, List<String> strlist) {
		// 파일 이름 받아서 생성하기
		file = new File("C:/tmp/"+filename+".txt");
		
		try {
			// 만들고... 있으면 걍 시~작
			if(file.createNewFile()) {
				System.out.println("생성 완료");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 문자열 리스트 받은 거 꺼내서 파일에 출력
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
			for(String str : strlist) {
				pw.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load(String filename, List<String> strlist) {
		// 파일 이름 받아서 읽어오기
		file = new File("C:/tmp/"+filename+".txt");
		try {
			// 혹시 모르니까 만들기도 넣기
			// 안 넣으면 없는 파일 못 불러와서 터짐
			if(file.createNewFile()) {
				System.out.println("생성 완료");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 파일 읽어와서
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String str = "";
			
			// 줄별로 스트링 리스트에 넣자
			while((str=br.readLine())!=null) {
				strlist.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
