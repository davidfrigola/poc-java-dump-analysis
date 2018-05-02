
default:
	@echo "Use build|run|load-heap|heapdump|load-thread|threaddump"
	
build:
	mvn clean install
run: build
	java -jar -Xmx256m -Xmx256m target\heap-analysis-0.0.1-SNAPSHOT.jar
	
load-heap:
	curl http://localhost:9999/doStore

heapdump:
	echo "TODO - grep the PID using sth like jps  | grep heap  | cut -d ' ' -f1"
	echo "jmap -dump:file=heapdump.hprof,format=b 31280"
	
load-thread:
	echo "TODO"

threaddump:
	echo "TODO"