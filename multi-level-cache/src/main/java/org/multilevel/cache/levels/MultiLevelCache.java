package org.multilevel.cache.levels;

import org.multilevel.cache.models.ReadResponse;
import org.multilevel.cache.models.WriteResponse;

public interface MultiLevelCache<Key,Value> {
    WriteResponse set(Key key, Value value);
    ReadResponse<Value> get(Key key);
}
