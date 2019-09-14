package top.docstorm.documentstormcommon.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import top.docstorm.documentstormcommon.constant.MessageConstants;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/1
 */
@Configuration
public class ActiveMqConfig {

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(MessageConstants.FILE_MESSAGE_TOPIC_NAME);
    }
    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    @Bean
    JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
        return messagingTemplate;
    }
}
