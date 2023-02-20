pwd
echo "Build is running ..."
mvn clean install
docker build . -t naresh546/loan:11-DT