# This script builds the angular project and deploys it as a spring boot application

cd frontend
ng build --prod --outputPath=../src/main/resources/static/
cd ..
./mvnw spring-boot:run
