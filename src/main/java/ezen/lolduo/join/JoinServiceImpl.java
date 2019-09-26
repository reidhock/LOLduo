package ezen.lolduo.join;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
@Service("joinService")
public class JoinServiceImpl implements JoinService {
	@Resource(name="joinDAO")
	private JoinDAO joinDAO;
	
	@Override
	public int selectUserID(Map<String, Object> map) throws Exception{
		return joinDAO.selectUserID(map);
	}
	
	@Override
	public void insertUserData(Map<String, Object> map) throws Exception{
		joinDAO.insertUserData(map);
	}

}
