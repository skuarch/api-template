package microservices.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.app.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class SqsConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqsConsumer.class);
    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    @SqsListener(value = "${aws.employee.recognition.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) throws JsonProcessingException {
        Profile profile = OBJECT_MAPPER.readValue(message, Profile.class);
        LOGGER.info("Message received {}", message);
        LOGGER.info("Profile {}", profile);
    }
}
