package com.mbc.addr.singleton;

import java.util.ArrayList;
import java.util.List;

import com.mbc.addr.dto.AddressBookDto;

public class Singleton {
	
	private static Singleton sc = null;
	public List<AddressBookDto> list;
	
	private Singleton() {
		list = new ArrayList<AddressBookDto>();
	}
	
	public static Singleton getInstance() {
		if(sc == null) {
			sc = new Singleton();
		}
		return sc;
	}

}
