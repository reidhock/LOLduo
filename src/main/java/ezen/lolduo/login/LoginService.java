package ezen.lolduo.login;

import java.util.Map;

import ezen.lolduo.common.CommandMap;

public interface LoginService {

	int loginChk(Map<String, Object> map) throws Exception;

	Map<String, Object> selectId(Map<String, Object> map) throws Exception;

}
