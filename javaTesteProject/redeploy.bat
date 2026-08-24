cd %JAVA_TEST_PROJECT_HOME%
start /wait mvnw package
start mvnw wildfly:deploy -DskipTests