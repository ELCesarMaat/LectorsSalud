package com.cetis22.lectorsalud.data;

import com.cetis22.lectorsalud.data.Result;
import com.cetis22.lectorsalud.data.model.LoggedInUser;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes4.dex */
public class LoginDataSource {
    public Result<LoggedInUser> login(String username, String password) {
        try {
            LoggedInUser fakeUser = new LoggedInUser(UUID.randomUUID().toString(), "Jane Doe");
            return new Result.Success(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
    }
}
