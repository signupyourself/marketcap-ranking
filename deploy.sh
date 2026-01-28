sudo systemctl stop webapp.service
sudo systemctl stop collector.service
sudo systemctl stop analyzer.service

mv applications/webapp/build/libs/webapp.war /home/azureuser/apps/webapp.war
mv applications/collector/build/libs/collector-all.jar /home/azureuser/apps/collector.jar
mv applications/analyzer/build/libs/analyzer-all.jar /home/azureuser/apps/analyzer.jar

sudo systemctl start webapp.service
sudo systemctl start collector.service
sudo systemctl start analyzer.service
