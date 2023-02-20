pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/card:11-DT