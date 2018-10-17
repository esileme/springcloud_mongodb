package com.zsgroup.xianhezi.api.security.model;

import com.zsgroup.xianhezi.common.model.UserInfo;

/**
 * The type Auth user factory.
 *
 * @author zhangxd
 */
public final class AuthUserFactory {

    private AuthUserFactory() {
    }

    /**
     * Create auth user.
     *
     * @param user the user
     * @return the auth user
     */
    public static AuthUser create(UserInfo user) {
        return new AuthUser(
                user.getId(),
                user.getPhone(),
                user.getPassword(),
                true
        );
    }

}
