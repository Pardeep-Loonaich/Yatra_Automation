SET HUBHOST=localhost
SET NODEHOST=localhost
IF NOT [%1] == [] SET HUBHOST=%1
IF NOT [%1] == [] SET NODEHOST=%1
start java -Dwebdriver.gecko.driver=geckodriver.exe -jar selenium-server-standalone-3.3.1.jar -role webdriver -hub http://%HUBHOST%:4444/grid/register -hubHost %HUBHOST% -host %NODEHOST% -port 5555 -browser "browserName=firefox, maxInstances=10"
Exit