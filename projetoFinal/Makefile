build:
	mvn clean package
	@echo "Built! ;)"

server:
	cd target; java -jar spring-boot-rest-2-0.0.1-SNAPSHOT.jar

push:
	git add .
	git commit -m "makefile"
	git push
	
clean:
	rm .attach_pid*
	@echo "Cleaned! ;)"


