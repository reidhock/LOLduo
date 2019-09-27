package ezen.lolduo.message;

import java.util.List;
import java.util.Map;

public interface MessageService {

	List<Map<String, Object>> messageList(Map<String, Object> map) throws Exception;

}
