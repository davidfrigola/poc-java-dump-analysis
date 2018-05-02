/**
 * Copyright 2018 Liberty Global (http://www.libertyglobal.com) and produced by Piksel (http://www.piksel.com)
 */
package es.davidfrigola.poc.heapanalysis.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InMemoryStorageController {


    private ConcurrentHashMap<String, Map<?,?>> storage = new ConcurrentHashMap<>();

    @RequestMapping("/doStore")
    public String doSomething(){
        Map<String,String> storeMe = new HashMap<>();
        for(int i=1;i<100000;i++){

            storeMe.put(Integer.toString(i), UUID.randomUUID()+"");
        }
        storage.put(Long.toString(System.currentTimeMillis()), storeMe);

        return "Stored " + storage.mappingCount() + " maps";
    }
}
