package com.mbc.addr.dto;

public class AddressBookDTO {

	// column(항목)
	private String name;
	private int age;
	private String phoneNum;
	private String address;
	private String memo;
	
	// constructor
	// 기본 생성자 필요 없어~ 라고 생각해도 기본적으로 만들어는 두셈
	public AddressBookDTO() {
	}
	
	public AddressBookDTO(String name, int age, String phoneNum, String address, String memo) {
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
		this.address = address;
		this.memo = memo;
	}
	
	// toString
	@Override
	public String toString() {
		return "이름: " + name + " | 나이: " + age + " | 전화번호: " + phoneNum + " | 주소: " + address
				+ " | 메모: " + memo + "";
	}
	
	public String write() {
		return name + "/" + age + "/" + phoneNum + "/" + address
				+ "/" + memo;
	}

	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	} 
	
	
}
