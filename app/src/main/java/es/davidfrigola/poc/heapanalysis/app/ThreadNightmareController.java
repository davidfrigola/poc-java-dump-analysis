package es.davidfrigola.poc.heapanalysis.app;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadNightmareController {

    private static final long TEN_MINUTES = 10*60*60*1000;

    @RequestMapping("/doThread")
    public String doThread(){

        for(int i=0;i<1000;i++){
            TaskExecutor executor = new SimpleAsyncTaskExecutor();
            executor.execute(() -> {
                try {
                    Thread.sleep(TEN_MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return "done";
    }

}
