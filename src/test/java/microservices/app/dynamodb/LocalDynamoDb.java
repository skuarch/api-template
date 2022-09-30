package microservices.app.dynamodb;

import microservices.app.service.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import java.io.IOException;

@Component
@ActiveProfiles("local")
public class LocalDynamoDb {

    @Autowired
    CommandRunner commandRunner;

    public void startLocalDynamoDb() throws IOException, InterruptedException {
        commandRunner.runCommand("sh scripts/dynamoDb.sh");
    }

    public void stopLocalDynamoDb(Long pid) throws IOException, InterruptedException {
        commandRunner.runCommand("kill -9 " + pid);
    }

    public void createTable(String tableName) {
        System.out.println(tableName);
    }

}
