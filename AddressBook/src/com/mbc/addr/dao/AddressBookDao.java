package com.mbc.addr.dao;

public interface AddressBookDao {

	void insert();
	void delete();
	void update();
	
	void selectName();
	void selectMemo();
	
	int search(String name);	
	void allprint();
	
	void fileSave();
	//void fileLoad();
}





