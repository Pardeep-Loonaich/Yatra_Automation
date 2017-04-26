SET HUBHOST=localhost
SET NODEHOST=localhost
IF NOT [%1] == [] SET HUBHOST=%1
IF NOT [%1] == [] SET NODEHOST=%1
start java -Dwebdriver.chrome.driver=chromedriver2.28.exe -jar selenium-server-standalone-3.3.1.jar -role webdriver -hub http://%HUBHOST%:4444/grid/register -hubHost %HUBHOST% -host %NODEHOST% -port 5556 -browser "browserName=chrome, maxInstances=10"
Exit
