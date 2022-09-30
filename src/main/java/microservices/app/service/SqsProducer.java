package microservices.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${aws.employee.recognition.queue.name}")
    private String nameQ;

    @Autowired
    public SqsProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public <T> void send(T message, Map<String, Object> headers) {
        if (message == null) {
            return;
        }

        queueMessagingTemplate.convertAndSend(nameQ, message, headers);
    }
}
