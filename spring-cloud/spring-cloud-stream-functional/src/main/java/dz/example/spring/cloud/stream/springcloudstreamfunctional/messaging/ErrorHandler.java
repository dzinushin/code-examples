package dz.example.spring.cloud.stream.springcloudstreamfunctional.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ErrorHandler {

    @StreamListener("errorChannel")
    public void errorHandler(ErrorMessage errorMessage) {
        Message<?> originalMessage = errorMessage.getOriginalMessage();
        byte[] payload = (byte[])originalMessage.getPayload();
        String s = new String(payload);
        log.error("Error happened for message payload: '{}'", s );
    }
}
