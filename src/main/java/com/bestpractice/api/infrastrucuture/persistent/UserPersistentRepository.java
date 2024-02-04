package com.bestpractice.api.infrastrucuture.persistent;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.context.annotation.Profile;

@Profile("!test")
public interface UserPersistentRepository {
    String newId();

    User findByEmail(String email);

    User findById(String id);

    User insert(User user);

    User replace(String id, User user);

    boolean removeById(String id);
}
