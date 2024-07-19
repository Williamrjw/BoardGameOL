package com.Williamrjw.game.common.user;

import com.Williamrjw.game.common.domain.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userService;

    UserController(UserServiceImpl userService){
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public UserVo getUserById(@PathVariable String id){
        return UserVo.UserVoMapper.INSTANCE.do2vo(userService.getById(id));
    }

    @GetMapping("/list/all")
    public List<UserVo> getUserListAll(){
        return UserVo.UserVoMapper.INSTANCE.dos2vos(userService.list());
    }
}
