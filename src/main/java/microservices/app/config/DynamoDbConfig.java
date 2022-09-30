package microservices.app.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@EnableDynamoDBRepositories(basePackages = "microservices.app.repository")
public class DynamoDbConfig {

    @Autowired
    private AwsConfig awsConfig;

    public AWSCredentialsProvider awsStaticCredentialsProvider() {
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey())
        );
        return awsStaticCredentialsProvider;
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withCredentials(awsStaticCredentialsProvider())
            .withEndpointConfiguration(
                new AwsClientBuilder
                    .EndpointConfiguration(awsConfig.getEndpoint(), awsConfig.getRegion()
                )
            ).build();
    }
}
