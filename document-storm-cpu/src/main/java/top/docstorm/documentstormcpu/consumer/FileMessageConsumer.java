package top.docstorm.documentstormcpu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.constant.MessageConstants;
import top.docstorm.documentstormcommon.service.MessageService;


/**
 * 消息消费者
 */
@Service
public class FileMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMessageConsumer.class);

    @Autowired
    private MessageService messageService;

    @JmsListener(destination = MessageConstants.TRANS_FILE_MESSAGE_TOPIC_NAME)
    public void receiveTransFileTopicMessage(String message) {
        LOGGER.info("receive message: " + message);
        messageService.dealTransFileTopicMessage(message);
    }
}
