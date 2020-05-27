package com.champlink.common.util.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQService {

	private Logger log = LoggerFactory.getLogger(ActiveMQService.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public boolean sendMsg(String destination, String message) {
		try {
			jmsTemplate.convertAndSend(destination, message);
			return true;
		} catch (Exception e) {
			log.error("ActiveMQService Send Message Error:" + e.getMessage());
			return false;
		}
	}

}
