package ezen.lolduo.tierBoard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ezen.lolduo.common.AbstractDAO;

@Repository("tierBoardDAO")
public class TierBoardDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("tierBoard.selectBoardList", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOne(Map<String, Object> map){
		return (Map<String, Object>)selectOne("tierBoard.selectId",map);
	}
	
	public void updateNameTier(Map<String, Object> map) {
		update("tierBoard.updateNameTier", map);
	}
}
