package org.lld.locker.util;

import org.lld.locker.exception.PackageSizeTooBigException;
import org.lld.locker.models.LockerSize;

public class LockerSizeUtil {
    public static LockerSize getLockerSizeForPack(double packSize)
            {
        if (packSize < 10) {
            return LockerSize.XS;
        } else if (packSize > 10 && packSize <= 20) {
            return LockerSize.S;
        } else if (packSize > 20 && packSize <= 40) {
            return LockerSize.M;
        } else if (packSize > 40 && packSize <= 50) {
            return LockerSize.L;
        } else if (packSize > 50 && packSize <= 70) {
            return LockerSize.XL;
        } else if (packSize > 70 && packSize <= 100) {
            return LockerSize.XXL;
        } else
            throw new PackageSizeTooBigException("Package it too big to be added in the locker");
    }
}
