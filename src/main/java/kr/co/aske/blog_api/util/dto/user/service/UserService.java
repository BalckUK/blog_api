package kr.co.aske.blog_api.util.dto.user.service;

import kr.co.aske.blog_api.util.dto.user.domain.UserInfo;
import kr.co.aske.blog_api.util.dto.user.domain.UserRole;
import kr.co.aske.blog_api.util.dto.user.repository.UserRepository;
import kr.co.aske.blog_api.util.dto.user.repository.UserRepositoryDsl;
import kr.co.aske.blog_api.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final UserRepositoryDsl repositoryDsl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryDsl.findByEmail(username)
                .orElseThrow(NullPointerException::new);
    }

    public ResponseDto delete(long id){
         repository.delete(
             UserInfo.builder()
                     .id(id)
                     .build()
        );
         return new ResponseDto(-1L);
    }


    public ResponseDto save(
            String email, String password, String nickName
    ){
        if(email.isEmpty()) throw new NullPointerException("email eq empty");
        if(password.isEmpty()) throw new NullPointerException("password eq empty");
        if(nickName.isEmpty()) throw new NullPointerException("nick eq empty");

        if(repositoryDsl.existByEmail(email)) throw new EntityExistsException("exist email : " + email);
        if(repositoryDsl.existByNick(nickName)) throw new EntityExistsException("exist nick : " + nickName);

        final Long id = repository.save(
                UserInfo.builder()
                        .email(email)
                        .password(new BCryptPasswordEncoder().encode(password))
                        .nickName(nickName)
                        .roles(Collections.singletonList(UserRole.USER))
                        .isEnable(true)
                        .build()
        ).getId();

        return new ResponseDto(id);
    }
}
