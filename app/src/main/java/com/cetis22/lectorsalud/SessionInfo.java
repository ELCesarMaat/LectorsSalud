package com.cetis22.lectorsalud;

public class SessionInfo {
    private static String email;
    private static String username;
    private static String name;
    private static String cedula;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionInfo.email = email;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionInfo.username = username;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        SessionInfo.name = name;
    }

    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula) {
        SessionInfo.cedula = cedula;
    }
}
