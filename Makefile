start:
	./mvnw spring-boot:run

native-start:
	./mvnw -Pnative native:compile -DskipTests
	./target/festival-ranking