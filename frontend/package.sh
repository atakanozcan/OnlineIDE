# This script builds the angular project and deploys it as a spring boot application

cd ui
ng build --prod --outputPath=../src/main/resources/static/
cd ..
./mvnw package -DskipTests
