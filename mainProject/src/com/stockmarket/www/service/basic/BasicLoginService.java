package com.stockmarket.www.service.basic;

import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.LoginService;

public class BasicLoginService implements LoginService{

	JdbcMemberDao memberDao;
	
	public BasicLoginService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public boolean isValidMember(String email, String pwd) {
		Member member = new Member();
		
		member = memberDao.getMemberByEmail(email);
		if(member != null) { 
			if(member.getPassword().equals(pwd))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean insertMember(Member member) {
		return false;
	}

	@Override
	public boolean deleteMember() {
		return false;
	}

	public int getIdbyEmail(String email) {
		Member member = new Member();
		
		member = memberDao.getMemberByEmail(email);
		return member.getId();
	}

}
