package org.multilevel.cache.providers;

public interface CacheProvider<Key,Value>{
    void set(Key key, Value value);
    Value get(Key key);
    double getUsage();
}
