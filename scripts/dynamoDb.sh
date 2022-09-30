#!/usr/bin/bash

# cd ../DynamoDB
java  -Xms256m -Xmx2048m -Djava.library.path=./DynamoDBLocal_lib -jar ./DynamoDB/DynamoDBLocal.jar -sharedDb -port 8001 &
