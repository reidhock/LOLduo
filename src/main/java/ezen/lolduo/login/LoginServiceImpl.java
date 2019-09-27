package ezen.lolduo.login;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="loginDAO")
	private LoginDAO loginDAO;
	
	@Override
	public int loginChk(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.loginChk(map);
	}

	@Override
	public Map<String, Object> selectId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.selectId(map);
	}

}
