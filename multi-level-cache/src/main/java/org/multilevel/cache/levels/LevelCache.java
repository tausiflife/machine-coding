package org.multilevel.cache.levels;

import org.multilevel.cache.models.CacheCharacteristics;
import org.multilevel.cache.models.ReadResponse;
import org.multilevel.cache.models.WriteResponse;
import org.multilevel.cache.providers.CacheProvider;

/**
 * Design pattern used is chain of responsibilities
 * @param <Key>
 * @param <Value>
 */
public class LevelCache<Key,Value> implements MultiLevelCache<Key,Value> {
    private final CacheCharacteristics characteristics;
    private final CacheProvider<Key,Value> provider;
    private final MultiLevelCache<Key,Value> next;

    public LevelCache(CacheCharacteristics characteristics, CacheProvider<Key, Value> provider, MultiLevelCache<Key, Value> next) {
        this.characteristics = characteristics;
        this.provider = provider;
        this.next = next;
    }

    @Override
    public WriteResponse set(Key key, Value value) {
        Double time= 0.0;
        Value currLevelValue = this.provider.get(key);
        time += this.characteristics.getReadTime();
        if(currLevelValue != value) {
            this.provider.set(key, value);
            time += this.characteristics.getWriteTime();
        }
        if(next != null) {
            WriteResponse nextLevelResponse = next.set(key, value);
            time += nextLevelResponse.getTotalTimeTaken();
        }
        return new WriteResponse(time);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        Double time = 0.0;
        Value val = this.provider.get(key);
        time +=characteristics.getReadTime();
        if(val == null) {
            if(next != null) {
                ReadResponse<Value> nextLevelResponse = next.get(key);
                time += nextLevelResponse.getTotalTime();
                if (nextLevelResponse != null) {
                    val = nextLevelResponse.getVal();
                    ;
                    this.provider.set(key, nextLevelResponse.getVal());
                    time += this.characteristics.getWriteTime();
                }
            }
        }
        return new ReadResponse<>(val, time);
    }
}
