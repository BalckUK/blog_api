package kr.co.aske.blog_api.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReqUpdateUserDto {
    private String nickName;

    public ReqUpdateUserDto(String nickName) {
        this.nickName = nickName;
    }
}
