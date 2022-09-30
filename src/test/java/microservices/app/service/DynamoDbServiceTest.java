package microservices.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class DynamoDbServiceTest {

    @Autowired
    private DynamoDbService dynamoDbService;

    @BeforeEach
    private void before() {
        String tableName = "testTableName";
        if (dynamoDbService.isTableAlreadyCreated(tableName)) {
            dynamoDbService.deleteTable(tableName);
        }
    }

    @Test
    public void createTableTest() throws InterruptedException {

        // given
        String tableName = "testTableName";


        // when
        dynamoDbService.createTable(tableName);
        boolean result = dynamoDbService.isTableAlreadyCreated(tableName);

        // then
        assertEquals(true, result);

    }

    @Test
    public void createTableTest1() throws InterruptedException {

        // given
        String tableName = "testTableName";
        dynamoDbService.createTable(tableName);

        // when
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            dynamoDbService.createTable(tableName);
        });

        // then
        assertNotNull(e);

    }

    @Test
    public void createTableWrongNameTest() throws InterruptedException {

        // given
        String tableName = "";


        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.createTable(tableName);
        });


        // then
        assertNotNull(ex);
    }

    @Test
    public void createTableWrongNameTest2() throws InterruptedException {

        // given
        String tableName = null;

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.createTable(tableName);
        });

        // then
        assertNotNull(ex);
    }


    @Test
    public void createTableWrongNameTest3() throws InterruptedException {

        // given
        String tableName = " ";

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.createTable(tableName);
        });

        // then
        assertNotNull(ex);
    }


    @Test
    public void isTableAlreadyCreated1() throws InterruptedException {

        // given
        String tableName = null;

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.isTableAlreadyCreated(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void isTableAlreadyCreated2() throws InterruptedException {

        // given
        String tableName = "";

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.isTableAlreadyCreated(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void isTableAlreadyCreated3() throws InterruptedException {

        // given
        String tableName = " ";

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.isTableAlreadyCreated(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void deleteTable1() throws InterruptedException {

        // given
        String tableName = null;

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.deleteTable(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void deleteTable2() throws InterruptedException {

        // given
        String tableName = " ";

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.deleteTable(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void deleteTable3() throws InterruptedException {

        // given
        String tableName = "";

        // when
        Exception ex = assertThrows(Exception.class, () -> {
            dynamoDbService.deleteTable(tableName);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    public void deleteTable4() throws InterruptedException {

        // given
        String tableName = "testTable";
        dynamoDbService.createTable(tableName);

        // when
        dynamoDbService.deleteTable(tableName);

        // then
        // table is deleted, no error is thrown

    }

}
