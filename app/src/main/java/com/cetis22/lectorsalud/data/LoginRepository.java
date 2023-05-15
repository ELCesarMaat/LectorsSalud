package com.cetis22.lectorsalud.data;

import com.cetis22.lectorsalud.data.Result;
import com.cetis22.lectorsalud.data.model.LoggedInUser;

/* loaded from: classes4.dex */
public class LoginRepository {
    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;
    private LoggedInUser user = null;

    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public void logout() {
        this.user = null;
        this.dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
    }

    public Result<LoggedInUser> login(String username, String password) {
        Result<LoggedInUser> result = this.dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser((LoggedInUser) ((Result.Success) result).getData());
        }
        return result;
    }
}
