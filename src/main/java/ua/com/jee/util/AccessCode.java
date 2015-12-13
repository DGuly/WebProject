package ua.com.jee.util;

import java.util.Random;

/**
 * Created by jsarafajr on 12/14/15.
 */
public class AccessCode {
    public static String generateCode() {
        Random rnd = new Random();
        Integer n = 100000 + rnd.nextInt(900000);
        return n.toString();
    }
}
