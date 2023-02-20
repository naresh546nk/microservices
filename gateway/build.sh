pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/gateway:11-DT