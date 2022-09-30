package microservices.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AwsConfig {

    @Value("${aws.access.key.id}")
    private String accessKey;
    @Value("${aws.access.secret.key}")
    private String secretKey;
    @Value("${aws.region}")
    private String region;
    @Value("${aws.endpoint}")
    private String endpoint;

    public AwsConfig() {
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
