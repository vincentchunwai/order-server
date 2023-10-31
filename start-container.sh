cd api-gateway
mvn clean install
cd ..

cd order-service
mvn clean install
cd ..

docker build -t api-gateway-image ./api-gateway-service
docker build -t order-service-image ./order-service

docker-compose up -d