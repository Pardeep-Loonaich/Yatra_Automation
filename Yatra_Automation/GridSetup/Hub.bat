SET HUBHOST=localhost
IF NOT [%1] == [] SET HUBHOST=%1
start java -jar selenium-server-standalone-3.4.0.jar -role hub
Exit