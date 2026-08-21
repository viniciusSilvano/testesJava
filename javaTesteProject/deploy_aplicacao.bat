cd %JAVA_TEST_PROJECT_HOME%
start /wait mvn clean package
start /wait mvn wildfly:undeploy
start /wait mvn wildfly:deploy -DskipTests