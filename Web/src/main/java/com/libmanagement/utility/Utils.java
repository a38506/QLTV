package com.libmanagement.utility;

import java.util.List;
import java.util.UUID;

public class Utils {
    public static String generateID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNullOrEmpty (List<Object> value ){
        return (value.isEmpty() || value == null);
    }

    public static boolean isNullOrEmpty (String value ){
        return (value.isEmpty() || value == null);
    }
}
