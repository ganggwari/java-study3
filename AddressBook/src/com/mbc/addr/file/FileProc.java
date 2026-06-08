package com.mbc.addr.file;

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

import com.mbc.addr.dto.AddressBookDto;
import com.mbc.addr.singleton.Singleton;

public class FileProc {

	private File file;
	
	public FileProc(String filename) {
		file = new File("C:/tmp/" + filename + ".txt");
		
		try {
			if(file.createNewFile()) {
				System.out.println("파일 생성 성공");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	// 데이터 저장
	public void save() {	
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
			Singleton single = Singleton.getInstance();
			
//			for (String str : arr) {
	//			pw.println(str);
	//		}
			for(AddressBookDto dto : single.list) {
				pw.println(dto.toString());				
			}			

			pw.close();
		
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	// 데이터 불러오기
	public void load() {
		
		//List<String> strlist = new ArrayList<String>();				
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
							
			Singleton single = Singleton.getInstance();
			
			String str = "";	
			while((str = br.readLine()) != null) {
				
				String arr[] = str.split("-");
				
				AddressBookDto dto = new AddressBookDto(arr[0], 
														Integer.parseInt(arr[1]), 
														arr[2], 
														arr[3], 
														arr[4]);
				single.list.add(dto);				
			}			
			br.close();			
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
//		String array[] = new String[strlist.size()];
//		for (int i = 0; i < strlist.size(); i++) {
//			array[i] = strlist.get(i);
//		}
//		
//		return array;
	}
}








