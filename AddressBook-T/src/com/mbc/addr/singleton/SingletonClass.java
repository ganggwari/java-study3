package com.mbc.addr.singleton;

import java.util.ArrayList;
import java.util.List;

import com.mbc.addr.dto.AddressBookDTO_T;


public class SingletonClass {
	private static SingletonClass sc = null;
	
	public List<AddressBookDTO_T> list;
	
	private SingletonClass() {
		list = new ArrayList<AddressBookDTO_T>();
	}
	
	public static SingletonClass getInstance() {
		if(sc==null) {
			sc = new SingletonClass();
		}
		return sc;
	}
}
