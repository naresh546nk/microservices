pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/eurekaservice:11-DT