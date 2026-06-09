package com.mbc.acc.single;

import java.util.ArrayList;
import java.util.List;

import com.mbc.acc.dto.AccountDto;

public class AccountSingleton {
	// list 만들기
	public List<AccountDto> list;
	static AccountSingleton as = null;
	
	// 생성자 잠그기
	private AccountSingleton() {
		// 여기서 리스트 만들기
		 list = new ArrayList<AccountDto>();
	}
	
	public static AccountSingleton getInstance() {
		if (as == null) {
			// 객체 생성(한 번만)
			as = new AccountSingleton();
		}
		return as;
	}
}
