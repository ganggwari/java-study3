package com.mbc.addr.dto;

public class AddressBookDTO_T {

	// column(항목)
	private String name;
	private int age;
	private String phoneNum;
	private String address;
	private String memo;
	
	// toString
	@Override
	public String toString() {
		return name + "/" + age + "/" + phoneNum + "/" + address + "/" + memo;
	}
	
	// 확인? 출력용 함수 info
	public void info() {
		// 아하... 나는 toString을 마개조했는데 선생님은 info를 따로 만드셨네...
		System.out.println("이름: " + name 
				+ " | 나이: " + age 
				+ " | 전화번호: " + phoneNum 
				+ " | 주소: " + address
				+ " | 메모: " + memo + "");
	}
	

	
	// constructor
	public AddressBookDTO_T() {
	}
	
	public AddressBookDTO_T(String name, int age, String phoneNum, String address, String memo) {
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
		this.address = address;
		this.memo = memo;
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
