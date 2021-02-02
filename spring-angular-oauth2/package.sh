cd frontend
ng build --prod --outputPath=../src/main/resources/static/
cd ..
./mvnw package -DskipTests
