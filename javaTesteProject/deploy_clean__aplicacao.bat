cd %JAVA_TEST_PROJECT_HOME%
start /wait mvn clean package
start /wait mvn wildfly:undeploy
start mvn wildfly:deploy -DskipTests