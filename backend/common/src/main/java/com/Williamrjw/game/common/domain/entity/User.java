package com.Williamrjw.game.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户信息
 */
@Data
public class User {
    @TableId
    private Long id;
    private String githubId;
    private String username;
    private String email;
}
