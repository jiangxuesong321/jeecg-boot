package org.jeecg.modules.message.handle.impl;

import org.jeecg.common.api.dto.message.MessageDTO;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.message.handle.ISendMsgHandle;

import java.util.List;

/**
* @Description: 发送系统消息
* @Author: wangshuai
* @Date: 2022年3月22日 18:48:20
*/
public class SystemSendMsgHandle implements ISendMsgHandle {

    public static final String FROM_USER="system";

    @Override
    public void SendMsg(String es_receiver, String es_title, String es_content) {
        if(oConvertUtils.isEmpty(es_receiver)){
            throw  new JeecgBootException("被发送人不能为空");
        }
        ISysBaseAPI sysBaseAPI = SpringContextUtils.getBean(ISysBaseAPI.class);
        MessageDTO messageDTO = new MessageDTO(FROM_USER,es_receiver,es_title,es_content);
        sysBaseAPI.sendSysAnnouncement(messageDTO);
    }

    @Override
    public void sendTemplateMail(String templateName, String mailInfo, List<String> toWho, List<String> ccPeoples) {

    }
}