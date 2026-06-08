package com.mbc.addr.file;

import java.awt.image.SinglePixelPackedSampleModel;
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

import com.mbc.addr.dto.AddressBookDTO_T;
import com.mbc.addr.singleton.SingletonClass;

public class FileProc_T {
	private File file;
	
	public FileProc_T(String filename) {
		file = new File("C:/tmp/"+filename+".txt");
		
		try {
			if( file.createNewFile()) {
				System.out.println("파일 생성 성공");
			}else {
				System.out.println("파일 생성 실패");
			}// 사실 else문은 굳이 필요가 없다. 이미 파일 있으면 생성을 안 하고 얘가 떠버리니까1!!!!!!
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	SingletonClass single = SingletonClass.getInstance();
	// data save
	public void save() {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			
			
			for(AddressBookDTO_T dto : single.list) {
				pw.println(dto.toString());
			}
//			for(String str:arr) {
//				pw.println(str);
//			}
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// data load
	public void load() {
		List<String> strList = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String str = "";
			while((str = br.readLine())!=null) {
				String[] arr = str.split("/",-1);
				AddressBookDTO_T dto = new AddressBookDTO_T(arr[0],
						Integer.parseInt(arr[1]), 
						arr[2], 
						arr[3], 
						arr[4]);
				single.list.add(dto);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
