package top.docstorm.documentstormcpu.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import top.docstorm.documentstormcommon.constant.MessageConstants;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.utils.FastJsonUtils;


/**
 * 消息消费者
 */
@Service
public class FileMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMessageConsumer.class);
    @JmsListener(destination = MessageConstants.FILE_MESSAGE_TOPIC_NAME)
    public void receiveTopicMessage(String message) {
        LOGGER.info("receive message: " + message);
        FileInfo fileInfo = FastJsonUtils.toBean(message, FileInfo.class);
    }
}
