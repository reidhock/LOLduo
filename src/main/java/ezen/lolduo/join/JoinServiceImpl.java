package ezen.lolduo.join;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service("joinService")
public class JoinServiceImpl implements JoinService {
	
	Logger log = Logger.getLogger(this.getClass());
	
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

	@Override
	public int selectSummonerName(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return joinDAO.selectSummonerName(map);
	}

}
