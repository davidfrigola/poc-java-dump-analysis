default:
	@echo "Use build|build-container|build-container-ci|run|run-container-ci|load-heap|heapdump|heampdump-ci|load-thread|threaddump|clean"
	
build:
	mvn clean package -f app/pom.xml

build-container:
	mvn clean install dockerfile:build -f app/pom.xml
build-container-ci:
	cd app
	docker build -t poc/heap-analysis .
	cd ..

run:
	java -jar -Xmx64m -Xmx64m app/target/heap-analysis-0.0.1-SNAPSHOT.jar
	
run-container-ci:
	docker run -d -p 9999:9999 --name test-ci -t poc/heap-analysis
stop-container-ci:
	docker stop test-ci
	docker rm test-ci


load-heap:
	curl http://localhost:9999/doStore

heapdump:
	@echo "Generating heapdump.hprof file...."
	jps | grep heap | cut -d ' ' -f1 | xargs jmap -dump:file=heapdump.hprof,format=b $1
	@echo "Heap dump generated"

heapdump-ci:
	docker exec -it test-ci jmap -dump:file=/tmp/heapdump.hprof,format=b 1

load-thread:
	curl http://localhost:9999/doThread


threaddump:
	sh scripts/dothreaddump.sh

clean:
	rm -f *.hprof
	rm -f *.tdump

