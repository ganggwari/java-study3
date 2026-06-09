package com.mbc.acc.dto;

public class AccountDto {

	private String ioKind;
	private int money;
	private String title;
	private String content;
	private int adate;
	
	public AccountDto() {
	}

	public AccountDto(String ioKind, int money, String title, String content, int adate) {
		super();
		this.ioKind = ioKind;
		this.money = money;
		this.title = title;
		this.content = content;
		this.adate = adate;
	}

	public String getIoKind() {
		return ioKind;
	}

	public void setIoKind(String ioKind) {
		this.ioKind = ioKind;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAdate() {
		return adate;
	}

	public void setAdate(int adate) {
		this.adate = adate;
	}

	@Override
	// 파일 출력용
	public String toString() {
		return ioKind + "/" + money + "/" + title + "/" + content + "/" + adate;
	}
	
	// 콘솔 출력용 
	public void info() {
		System.out.println(ioKind + " | 금액: " + money + " | " + title + ": " + content
				+ " | 날짜: " + adate + " |");
	}
	
}
