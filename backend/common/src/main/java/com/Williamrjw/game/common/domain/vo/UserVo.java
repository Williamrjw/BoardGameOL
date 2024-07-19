package com.Williamrjw.game.common.domain.vo;

import com.Williamrjw.game.common.core.IVoMapper;
import com.Williamrjw.game.common.domain.entity.User;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Data
public class UserVo {

    private Long id;

    private String githubId;

    private String username;

    private String email;

    @Mapper
    public interface UserVoMapper extends IVoMapper<UserVo, User> {
        UserVoMapper INSTANCE = Mappers.getMapper(UserVoMapper.class);
    }
}
