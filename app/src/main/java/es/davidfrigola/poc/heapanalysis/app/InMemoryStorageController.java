package es.davidfrigola.poc.heapanalysis.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InMemoryStorageController {


    private static final String DEFAULT_ITEMS = "10000";
	
	@Autowired
	private StorageService storageService;

    @RequestMapping("/doStore")
    public String doSomething(@RequestParam(name="items",defaultValue=DEFAULT_ITEMS) Integer items){

        storageService.add(storageService.generate(items));

        return "Stored " + storageService.size() + " maps";
    }
}
