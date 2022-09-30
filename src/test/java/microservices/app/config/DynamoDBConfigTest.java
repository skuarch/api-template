package microservices.app.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.io.IOException;


@SpringBootTest
@ActiveProfiles("local")
class DynamoDBConfigTest {

    @Autowired
    private DynamoDbConfig dynamoDBConfig;


    @Test
    public void buildAmazonDynamoDB() throws IOException, InterruptedException {

        // given
        Assert.assertNotNull(dynamoDBConfig);

        // when
        AmazonDynamoDB amazonDynamoDB = dynamoDBConfig.amazonDynamoDB();

        // then
        Assert.assertNotNull(amazonDynamoDB);

    }

}
