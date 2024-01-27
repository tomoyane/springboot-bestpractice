package com.bestpractice.api.infrastrucuture.repository;

import com.bestpractice.api.infrastrucuture.entity.UserKey;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Profile("!test")
public interface UserKeyRepository extends JpaRepository<UserKey, Long> {

    UserKey findByUserId(Long userId);

    UserKey save(UserKey userKey);

}
