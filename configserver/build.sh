pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/configserver:11-DT