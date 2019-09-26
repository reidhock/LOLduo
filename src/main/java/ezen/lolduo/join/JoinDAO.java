package ezen.lolduo.join;

import java.util.Map;

import org.springframework.stereotype.Repository;

import ezen.lolduo.common.AbstractDAO;

@Repository("joinDAO")
public class JoinDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public int selectUserID(Map<String, Object> map) throws Exception{
		return Integer.parseInt(selectOne("join.selectUserID",map).toString());
	}
	
	public void insertUserData(Map<String, Object> map) throws Exception{
		insert("join.insertUserData",map);
	}
	
	public int selectSummonerName(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		return Integer.parseInt(selectOne("join.selectSummonerName", map).toString());
	}
}
