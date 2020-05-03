package com.tomandrieu.utilities;

public class LookupUtil {

    public static <E extends Enum<E>> E lookup(Class<E> e, String id) throws RuntimeException {
        E result;
        try {
            result = Enum.valueOf(e, id);
        } catch (IllegalArgumentException exception) {
            // log error or something here
            throw new RuntimeException(
                    "Invalid value for enum " + e.getSimpleName() + ": " + id);
        }

        return result;
    }
}