package com.cetis22.lectorsalud;

/* loaded from: classes5.dex */
public class UserHelperClass {
    String email;
    String name;
    String password;
    String tiposangre;
    String username;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String email, String password, String tiposangre) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tiposangre = tiposangre;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTiposangre() {
        return this.tiposangre;
    }

    public void setTiposangre(String tiposangre) {
        this.tiposangre = tiposangre;
    }
}
