package ezen.lolduo.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="messageDAKO")
	private MessageDAO messageDAO;
	
	@Override
	public List<Map<String, Object>> messageList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return messageDAO.messageList(map);
	}

}
