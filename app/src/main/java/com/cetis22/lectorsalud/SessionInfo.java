package com.cetis22.lectorsalud;

/* loaded from: classes5.dex */
public class SessionInfo {
    private static String cedula;
    private static String email;
    private static String hospital;
    private static byte[] img_user;
    private static String name;
    private static String username;

    public static byte[] getImg_user() {
        return img_user;
    }

    public static String getHospital() {
        return hospital;
    }

    public static void setHospital(String hospital2) {
        hospital = hospital2;
    }

    public static void setImg_user(byte[] img_user2) {
        img_user = img_user2;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email2) {
        email = email2;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username2) {
        username = username2;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name2) {
        name = name2;
    }

    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula2) {
        cedula = cedula2;
    }
}
