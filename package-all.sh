cd compiler
./mvnw package -DskipTests

cd ../dark-mode
./mvnw package -DskipTests

cd ../frontend
bash package.sh

cd ../project
./mvnw package -DskipTests

cd ../spring-angular-oauth2
bash package.sh

cd ../discoveryserver
./mvnw package -DskipTests

cd ../gateway
./mvnw package -DskipTests

echo "EVERYTHING PACKAGED :)"
