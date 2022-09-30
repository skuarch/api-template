# api-employee-recognition-template

# Description
Spring boot application with AWS, DynamoDB and SQS
Template to create microservices

This project only works on linux or WSL

# Requirements
- java 8
- MVN 
- Valid AWS user
- Git

# Environment
There are 3 environments
- develop
- qa 
- prod

This environment variables are requires **before** run the application


> export readonly SPRINGBOOT_PROFILE=develop

> export readonly AWS_ACCESS_KEY_ID=123

> export readonly AWS_SECRET_ACCESS_KEY=123


Replace the values with the correct one.


# Run application
Clone this repository
This projects needs at least 3 environment variables (see scripts/envVar.sh)

execute this command to create those variables 

>source scripts/envVars.sh


the command is going to export and load the variables in the current session

In the root folder execute this command

>mvn spring-boot-run


# Run DynamoDB locally only for development
Inside of scripts folder there are one script to start the database
> sh scripts/dynamoDb.sh

the port **8001** is going to be use by dynamoDB

If you have any problem just run the command 
>java -Xms256m -Xmx2048m -Djava.library.path=./DynamoDBLocal_lib -jar ./DynamoDB/DynamoDBLocal.jar -sharedDb -port 8001

# Run scripts
In order to run the bash scripts (scripts folder) do it outside of scripts folder
