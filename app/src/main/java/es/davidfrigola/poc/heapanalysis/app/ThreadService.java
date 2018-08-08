package es.davidfrigola.poc.heapanalysis.app;

import java.util.function.Function;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

	public void generateThreads(String prefix, Long delay, Integer threads,Function<Void, Void> execution) {
		for(int i=0;i<threads;i++){
            TaskExecutor executor = new SimpleAsyncTaskExecutor(prefix);
            executor.execute(() -> {
                try {
                	execution.apply(null);
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
	}
}
