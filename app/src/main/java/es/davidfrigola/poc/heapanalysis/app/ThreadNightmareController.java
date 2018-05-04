package es.davidfrigola.poc.heapanalysis.app;

import java.util.concurrent.TimeUnit;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadNightmareController {

    private static final long DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    
    private static final String THREAD_NAME_PREFIX =  "ThreadsNighmare";

    @RequestMapping("/doThread")
    public String doThread(){

        for(int i=0;i<1000;i++){
            TaskExecutor executor = new SimpleAsyncTaskExecutor(THREAD_NAME_PREFIX);
            executor.execute(() -> {
                try {
                    Thread.sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return "done";
    }

}
