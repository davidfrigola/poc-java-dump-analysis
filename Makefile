default:
	@echo "Use build|run|load-heap|heapdump|load-thread|threaddump|clean"
	
build:
	mvn clean package -f app/pom.xml
run:
	java -jar -Xmx256m -Xmx256m app/target/heap-analysis-0.0.1-SNAPSHOT.jar
	
load-heap:
	curl http://localhost:9999/doStore

heapdump:
	@echo "Generating heapdump.hprof file...."
	jps | grep heap | cut -d ' ' -f1 | xargs jmap -dump:file=heapdump.hprof,format=b $1
	@echo "Heap dump generated"
load-thread:
	curl http://localhost:9999/doThread

threaddump:
	sh scripts/dothreaddump.sh

clean:
	rm -f *.hprof
	rm -f *.tprof

