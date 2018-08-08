package es.davidfrigola.poc.heapanalysis.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class StorageService {
	
	private ConcurrentHashMap<String, Map<?,?>> storage = new ConcurrentHashMap<>();
	
	public void add(Map<?,?> map){
		storage.put(Long.toString(System.currentTimeMillis()), map);
	}
	
	public void clear(){
		
		storage = new ConcurrentHashMap<>();
	}
	
	public Map<?,?> generate(int items){
		Map<String,String> storeMe = new HashMap<>();
        for(int i=1;i<items;i++){

            storeMe.put(Integer.toString(i), UUID.randomUUID()+"");
        }
        
        return storeMe;
	}
	
	public long size(){
		return storage.mappingCount();
		
	}

}
