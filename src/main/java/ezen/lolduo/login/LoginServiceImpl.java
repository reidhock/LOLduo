package ezen.lolduo.login;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;
	
	@Override
	public int loginChk(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.loginChk(map);
	}

}
