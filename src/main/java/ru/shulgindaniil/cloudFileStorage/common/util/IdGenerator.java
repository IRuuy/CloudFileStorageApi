package ru.shulgindaniil.cloudFileStorage.common.util;

import java.util.Random;

public class IdGenerator {
    public static String generate(int countBytes) {
        final byte[] bytes = new byte[countBytes];
        new Random().nextBytes(bytes);
        return Base58Encoder.encode(bytes);
    }
}
