cd %JAVA_TEST_PROJECT_HOME%
start /wait mvn package
start mvn wildfly:deploy -DskipTests