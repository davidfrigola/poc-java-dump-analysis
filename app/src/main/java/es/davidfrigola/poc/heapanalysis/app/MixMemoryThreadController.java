package es.davidfrigola.poc.heapanalysis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MixMemoryThreadController {

	private static final String DEFAULT_ITEMS= "1000";
	private static final String DEFAULT_THREADS = "1000";

    private static final String THREAD_NAME_PREFIX =  "MixMemoryThread";
	private static final String DEFAULT_DELAY_MS = "60000";
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ThreadService threadService;
	
	@RequestMapping("/doMix")
	public String doMix(@RequestParam(name="items",defaultValue=DEFAULT_ITEMS) Integer items,@RequestParam(name="delay",defaultValue=DEFAULT_DELAY_MS) Long delay,
    		@RequestParam(name="threads",defaultValue=DEFAULT_THREADS) Integer threads){

		threadService.generateThreads(THREAD_NAME_PREFIX, delay, threads, 
				(Void) -> {storageService.add(storageService.generate(items)); return null; });

		return "done";
		
	}

}
