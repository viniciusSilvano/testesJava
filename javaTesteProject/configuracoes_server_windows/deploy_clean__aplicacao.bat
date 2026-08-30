cd %JAVA_TEST_PROJECT_HOME%
start /wait mvnw clean package
start /wait mvnw wildfly:undeploy
start mvn wildfly:deploy -DskipTests