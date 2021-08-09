package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Profile("!test")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByIdAndUuid(Long id, String uuid);

    User save(User user);

    User removeById(Long id);
}
