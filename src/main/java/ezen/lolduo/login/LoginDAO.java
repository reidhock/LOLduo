package ezen.lolduo.login;

import java.util.Map;

import org.springframework.stereotype.Repository;

import ezen.lolduo.common.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{

	public int loginChk(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(selectOne("login.selectLoginCheck",map).toString());
	}

}
