package com.example.timeconverter.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.Conversion;

@Service
public class CacheService {
    private static final int MAX_ENTRIES = 1;

    private final Map<String, List<Conversion>> cache = new LinkedHashMap<String, List<Conversion>>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, List<Conversion>> eldest) {
            return size() > MAX_ENTRIES;
        }
    };

    public List<Conversion> getFromCache(String key) {
        return cache.get(key);
    }

    public void putInCache(String key, List<Conversion> data) {
        cache.put(key, data);
    }

    public int getSize() {
        return cache.size();
    }
    
    public Set<String> getKeys() {
        return cache.keySet();
    }
    
    public void clear() {
        cache.clear();
    }
}
