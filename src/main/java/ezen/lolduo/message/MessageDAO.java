package ezen.lolduo.message;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ezen.lolduo.common.AbstractDAO;

@Repository("messageDAO")
public class MessageDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> messageList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("message.messageList",map);
	}

}
