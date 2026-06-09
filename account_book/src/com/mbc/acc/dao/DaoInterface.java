package com.mbc.acc.dao;

public interface DaoInterface {

	void insert(); // 입력. 수입/지출 + 액수 + 제목 + 내용 + 날짜
	void delete(); // 삭제. 검색어 돌려서 해당하는 거 다 나오게 하고, 삭제 전에 확인 1회
	void select(); // 검색.
	void update(); // 수정. 원하는 카테고리 수정하게 해야지...
	void print(); // 출력. 전체 출력 메뉴/기간 지정 출력 메뉴
	void month(); // 결산. 수입 지출 계산해서 적자 흑자 결산
	void save(); // 정보 저장
}
