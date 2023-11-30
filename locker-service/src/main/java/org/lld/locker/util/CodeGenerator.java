package org.lld.locker.util;

import org.apache.commons.lang.RandomStringUtils;

public class CodeGenerator {
    public static String generateCode(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
