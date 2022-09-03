package com.dicapisar.userManager.utils;

public class PasswordGenerator {
    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String ESPECIALES = "ñÑ";

    public static String SIMBOLOS = "!#$%&/()=?¡@><.,";


    public static String getPassword() {
        return getPassword(16);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES + SIMBOLOS, length);
    }

    public static String getPassword(String key, int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(key.charAt((int) (Math.random() * key.length())));
        }

        return password.toString();
    }
}
