package ezen.lolduo.tierBoard;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service; 

@Service("tierBoardService")
public class TierBoardServiceImpl implements TierBoardService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="tierBoardDAO")
	private TierBoardDAO tierBoardDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return tierBoardDAO.selectBoardList(map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectId(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) tierBoardDAO.selectOne(map);
	}
	
	public void updateNameTier(Map<String, Object> map) throws Exception{
		tierBoardDAO.updateNameTier(map);
	}

}
