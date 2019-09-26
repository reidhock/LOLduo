package ezen.lolduo.tierBoard;

import java.util.List;
import java.util.Map;

import ezen.lolduo.common.CommandMap;

public interface TierBoardService {

	List<Map<String, Object>> selectBoardList(Map<String, Object> commandMap) throws Exception;


}
