package com.mbc.jdbc.dto;

public class MemberDto {
	private String user_id;
	private String name;
	private int age;
	private String joindate;
	
	
	public MemberDto() {
	}
	
	public MemberDto(String user_id, String name, int age, String joindate) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.age = age;
		this.joindate = joindate;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

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

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "MemberDto [user_id=" + user_id + ", name=" + name + ", age=" + age + ", joindate=" + joindate + "]";
	}

	
}
