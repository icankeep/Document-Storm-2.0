package top.docstorm.documentstormcommon.service;

import javax.jms.Topic;

/**
 * @Description: 提供消息相关的服务，发送消息等
 * @author: passer
 * @version：2019/9/19
 */
public interface MessageService {
    void sendTransFileTopicMessage(String message);

    void sendMessage(Topic topic, String message);

    void dealTransFileTopicMessage(String message);
}
