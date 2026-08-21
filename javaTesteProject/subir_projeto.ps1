& "$env:WILDFLY_HOME/bin/jboss-cli.bat" --connect --command=:shutdown
& "$env:WILDFLY_HOME/bin/standalone.bat"
cd "$env:JAVA_TEST_PROJECT_HOME"
mvn wildfly:deploy -DskipTests 