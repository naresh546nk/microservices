TAG=12-M
pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/loan:$TAG