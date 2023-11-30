package org.lld.locker.generator;

import java.util.UUID;

public class DefaultIdGenerator implements IdGenerator{
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
