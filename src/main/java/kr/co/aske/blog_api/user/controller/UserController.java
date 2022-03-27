package kr.co.aske.blog_api.user.controller;

import kr.co.aske.blog_api.annotation.LoginUser;
import kr.co.aske.blog_api.user.domain.UserInfo;
import kr.co.aske.blog_api.user.dto.request.ReqSaveUserDto;
import kr.co.aske.blog_api.user.dto.request.ReqUpdateUserDto;
import kr.co.aske.blog_api.user.dto.response.UserDto;
import kr.co.aske.blog_api.user.service.UserService;
import kr.co.aske.blog_api.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping(value = "/sign")
    public ResponseDto save(
            ReqSaveUserDto dto
    ) {
        return service.save(
                dto.getEmail(), dto.getPassword(),
                dto.getNickName()
        );
    }

    @GetMapping
    public UserDto myInfo(@LoginUser UserInfo info){

        return new UserDto(info);
    }

    @GetMapping(value = "/token")
    public CsrfToken getToken(CsrfToken token){
        return token;
    }


    @GetMapping(value = "/delete")
    public ResponseDto userdelete(long id){
         return service.delete(id);
    }

    @PutMapping(value = "")
    public ResponseDto put(
            ReqUpdateUserDto dto,
            @LoginUser UserInfo userInfo
    ){
        return service.update(userInfo.getId(), dto.getNickName());
    }

}
