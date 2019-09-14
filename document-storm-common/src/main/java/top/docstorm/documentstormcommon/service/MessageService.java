package top.docstorm.documentstormcommon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import javax.jms.Topic;

/**
 * @Description: 提供消息相关服务<p>
 * @author: passer<p>
 * @version：${DATE}<p>
 */
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Topic topic;

    public void sendTopicMessage(String message) {
        jmsTemplate.convertAndSend(topic, message);
    }

//    @JmsListener(destination = MessageConstants.FILE_MESSAGE_TOPIC_NAME)
//    public void receiveTopicMessage(String message) {
//        System.out.println(message);
//    }

}
