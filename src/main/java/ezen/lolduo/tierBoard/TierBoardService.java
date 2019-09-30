package ezen.lolduo.tierBoard;

import java.util.List;
import java.util.Map;

import ezen.lolduo.common.CommandMap;

public interface TierBoardService {

	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

	Map<String, Object> selectId(Map<String, Object> map) throws Exception;

	void updateNameTier(Map<String, Object> map) throws Exception;


}
