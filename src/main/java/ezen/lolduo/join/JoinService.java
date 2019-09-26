package ezen.lolduo.join;

import java.util.Map;

public interface JoinService {
	void insertUserData(Map<String, Object> map) throws Exception;
	
	int selectUserID(Map<String, Object> map) throws Exception;

}
