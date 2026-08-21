start /wait %WILDFLY_HOME%/bin/jboss-cli.bat --connect --command=:shutdown
start %WILDFLY_HOME%/bin/standalone.bat