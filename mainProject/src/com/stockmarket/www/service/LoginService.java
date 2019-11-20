package com.stockmarket.www.service;

import com.stockmarket.www.entity.Member;

public interface LoginService {
	
//	등록된 회원인지 체크
	boolean isValidMember(String userId, String pwd);
	
//	회원등록
	boolean insertMember(Member member);
	
//	회원탈퇴
	boolean deleteMember();
}