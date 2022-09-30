package microservices.app.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DynamoDbService {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void createTable(String tableName) throws InterruptedException {

        if (tableName == null || tableName.isEmpty() || tableName.trim().length() < 1) {
            throw new IllegalArgumentException("table name is not correct");
        }

        if (isTableAlreadyCreated(tableName)) {
            throw new IllegalArgumentException("table " + tableName + " already exists");
        }

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));

        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
            .withTableName(tableName)
            .withKeySchema(keySchema)
            .withAttributeDefinitions(attributeDefinitions)
            .withProvisionedThroughput(
                new ProvisionedThroughput()
                    .withReadCapacityUnits(1L)
                    .withWriteCapacityUnits(1L)
            );

        Table table = dynamoDB.createTable(request);
        TableDescription tableDescription = table.waitForActive();
        logger.info("table created " + tableDescription.getTableId());

    }

    public boolean isTableAlreadyCreated(String tableName) {

        if (tableName == null || tableName.isEmpty() || tableName.trim().length() < 1) {
            throw new IllegalArgumentException("table name is not correct");
        }

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        TableCollection<ListTablesResult> tableCollection = dynamoDB.listTables();
        Iterator<Table> iterator = tableCollection.iterator();

        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (table.getTableName().equalsIgnoreCase(tableName)) {
                logger.info("table " + tableName + " deleted");
                return true;
            }
        }

        return false;
    }

    public void deleteTable(String tableName) {

        if (tableName == null || tableName.isEmpty() || tableName.trim().length() < 1) {
            throw new IllegalArgumentException("table name is not correct");
        }

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable(tableName);
        table.delete();

    }

}
