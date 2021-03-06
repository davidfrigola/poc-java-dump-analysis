package es.davidfrigola.poc.heapanalysis.app;

import java.util.concurrent.TimeUnit;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadNightmareController {

    private static final Long DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    
    private static final String THREAD_NAME_PREFIX =  "ThreadsNighmare";
	private static final String DEFAULT_DELAY_MS = "60000";

	private static final String DEFAULT_THREADS = "1000";

    @RequestMapping("/doThread")
    public String doThread(@RequestParam(name="delay",defaultValue=DEFAULT_DELAY_MS) Long delay,
    		@RequestParam(name="threads",defaultValue=DEFAULT_THREADS) Integer threads){

        for(int i=0;i<threads;i++){
            TaskExecutor executor = new SimpleAsyncTaskExecutor(THREAD_NAME_PREFIX);
            executor.execute(() -> {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return "done";
    }

}
