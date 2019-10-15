package top.docstorm.documentstormcommon.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import top.docstorm.documentstormcommon.constant.MessageConstants;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.exception.LogicException;
import top.docstorm.documentstormcommon.service.MessageService;
import top.docstorm.documentstormcommon.service.TransService;

import javax.annotation.Resource;
import javax.jms.Topic;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/19
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private GetTransService getTransService;

    @Resource(name = MessageConstants.TRANS_FILE_MESSAGE_TOPIC_NAME)
    private Topic topic;

    @Override
    public void sendMessage(Topic topic, String message) {
        try {
            jmsMessagingTemplate.convertAndSend(topic, message);
            LOGGER.info("发送消息成功：" + message);
        } catch (Exception e) {
            throw new LogicException("发送文件转换消息失败: " + message);
        }
    }

    @Override
    public void dealTransFileTopicMessage(String message) {
        FileInfo fileInfo = JSON.parseObject(message, FileInfo.class);
        if (fileInfo == null) {
            throw new LogicException("FileInfo为空！");
        }
        TransService transService = getTransService.getTransService(fileInfo.getFileFormatChangeType());
        transService.trans(fileInfo);
    }

    @Override
    public void sendTransFileTopicMessage(String message) {
        try {
            jmsMessagingTemplate.convertAndSend(topic, message);
            LOGGER.info("发送消息成功：" + message);
        } catch (Exception e) {
            throw new LogicException("发送文件转换消息失败: " + message);
        }
    }

}
