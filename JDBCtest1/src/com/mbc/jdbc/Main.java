package com.mbc.jdbc;

import java.util.List;

import com.mbc.jdbc.dao.MemberDao;
import com.mbc.jdbc.dto.MemberDto;

public class Main {
	public static void main(String[] args) {
		
		MemberDao dao = new MemberDao();
		/*
		String user_id = "aaa12";
		String name = "paul";
		int age = 63;
		
		int count = dao.insert(user_id, name, age);
		if (count >0) {
			System.out.println("정상처리되었씁니다");
		}
		*/
		
		/*
		int cnt = dao.delete("aaa12");
		
		if (cnt >0) {
			System.out.println("정상처리되었씁니다");
		}
		*/
		
		/*
		String userid = "bcd27";
		
		MemberDto dto = dao.selectOne(userid);
		if(dto == null) {
			System.out.println("데이터를 찾을 수 없습니다");
		} else {
			System.out.println(dto.toString());
		}
		*/
		
		/*
		 * List<MemberDto> list = dao.selectList(); for(MemberDto d:list) {
		 * System.out.println(d.toString()); }
		 */
		
		
		int count = dao.update("abc23", "samy");
		if(count>0) {
			System.out.println("정상적으로 수정되었습니다아");
		}
		
	}
}
