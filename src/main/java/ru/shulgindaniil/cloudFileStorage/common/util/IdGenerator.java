package ru.shulgindaniil.cloudFileStorage.common.util;

import java.util.Base64;
import java.util.Random;

public class IdGenerator {
    public static String generate(int countBytes) {
        final byte[] bytes = new byte[countBytes];
        new Random().nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.withoutPadding().encodeToString(bytes);
    }
}
