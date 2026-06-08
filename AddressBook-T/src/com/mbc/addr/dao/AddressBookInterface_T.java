package com.mbc.addr.dao;

public interface AddressBookInterface_T {
	void insert();
	void delete();
	void update();
	
	void selectName();
	void selectMemo();
	
	int search(String name);
	void allprint();

	void fileSave();
	
	
}
