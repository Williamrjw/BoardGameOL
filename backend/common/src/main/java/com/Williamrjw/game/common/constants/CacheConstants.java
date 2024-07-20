package com.Williamrjw.game.common.constants;

/**
 * 缓存的key 常量
 *
 */
public class CacheConstants
{
    private CacheConstants() {

    }

    public static final String SYSTEM_NAME = "base:";

    public static final String TICKET_TOKEN_KEY = SYSTEM_NAME + "ticket_tokens:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

}
