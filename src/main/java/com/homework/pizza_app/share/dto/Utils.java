package com.homework.pizza_app.share.dto;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author dritan
 */
//@Component
public class Utils {

    private final static Random RANDOM = new SecureRandom();
    private final static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static boolean CheckOrder(OrderDto order) {
        boolean present = true;

        List<String> list = new ArrayList<>();
        list.add("Hawaii");
        list.add("Regina");
        list.add("Quattro-Formaggi");
        if (!list.contains(order.getFlavor())) {
            present = false;
        }

        if (!((order.getSize().equalsIgnoreCase("L")) || (order.getSize().equalsIgnoreCase("M")))) {
            present = false;
        }

        if (!order.getCrust().equalsIgnoreCase("Thin")) {
            present = false;
        }
        return present;
    }

    public static String generateUserId(int length) {
        return generateRandomString(length);
    }

    public static String generateOrderId(int length) {
        return generateRandomString(length);
    }

    private static String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

}
