package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserKey;
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
