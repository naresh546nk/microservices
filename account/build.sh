pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/account:11-DT