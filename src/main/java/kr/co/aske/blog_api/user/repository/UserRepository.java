package kr.co.aske.blog_api.user.repository;

import kr.co.aske.blog_api.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    @Query("select p from UserInfo p")
    Stream<UserInfo> list();
}
