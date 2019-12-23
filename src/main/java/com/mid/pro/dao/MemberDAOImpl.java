package com.mid.pro.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mid.pro.model.MemberVO;
import com.mid.pro.util.Pager;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession sqlSession;
	private final static String NAMESPACE = "memberMapper.";
	
	@Override
	public int memberJoin(MemberVO memberVO)throws Exception{
		return sqlSession.insert(NAMESPACE+"memberJoin", memberVO);	
	}
	
	@Override
	public MemberVO memberCheckId(String id)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberCheckId", id);
	}
	
	@Override
	public MemberVO memberCheckEmail(String email)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberCheckEmail", email);
	}
	
	@Override
	public void createAuthKey(String email, String authKey)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email", email);
		map.put("authKey", authKey);
		
		sqlSession.selectOne(NAMESPACE+"createAuthKey", map);
	}
	
	@Override
	public void userAuth(String email)throws Exception{
		sqlSession.update(NAMESPACE+"userAuth", email);
	}
	
	
		
	@Override
	public MemberVO memberLogin(MemberVO memberVO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberLogin", memberVO);		
	}
	
	@Override
	public int memberUpdate(MemberVO memberVO)throws Exception{
		return sqlSession.update(NAMESPACE+"memberUpdate", memberVO);
	}
	
	@Override
	public int memberDelete(MemberVO memberVO)throws Exception{
		return sqlSession.delete(NAMESPACE+"memberDelete", memberVO);
	}

	@Override
	public List<MemberVO> List(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE + "allmember", pager);
	}
	
	@Override
	public int Count(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "memberCount", pager);
	}
	
	@Override
	public MemberVO memberSelect(MemberVO memberVO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberSelect", memberVO);
	}
	@Override
	public int memberUpdate2(MemberVO memberVO)throws Exception{
		return sqlSession.update(NAMESPACE+"memberUpdate2", memberVO);
	}
	

	

}
