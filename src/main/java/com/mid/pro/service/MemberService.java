package com.mid.pro.service;

import com.mid.pro.model.MemberVO;

public interface MemberService {

	public int memberJoin(MemberVO memberVO)throws Exception;
		
	public MemberVO memberCheckId(String id)throws Exception;
	
	public int memberCheckEmail(String email)throws Exception;
		
	//public void userAuth(String userEmail)throws Exception;
	
	public MemberVO memberLogin(MemberVO memberVO)throws Exception;
		
	public int memberUpdate(MemberVO memberVO)throws Exception;
	
	public int memberDelete(MemberVO memberVO)throws Exception;
	
}
