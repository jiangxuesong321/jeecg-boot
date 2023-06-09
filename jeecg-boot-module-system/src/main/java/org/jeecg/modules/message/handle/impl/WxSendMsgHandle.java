package org.jeecg.modules.message.handle.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.message.handle.ISendMsgHandle;

import java.util.List;

/**
 * @Description: 发微信消息模板
 * @author: jeecg-boot
 */
@Slf4j
public class WxSendMsgHandle implements ISendMsgHandle {

	@Override
	public void SendMsg(String es_receiver, String es_title, String es_content) {
		// TODO Auto-generated method stub
		log.info("发微信消息模板");
	}

	@Override
	public void sendTemplateMail(String templateName, String mailInfo, List<String> toWho, List<String> ccPeoples) {

	}

}
