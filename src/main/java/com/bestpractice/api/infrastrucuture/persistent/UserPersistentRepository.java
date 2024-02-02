package com.bestpractice.api.infrastrucuture.persistent;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.context.annotation.Profile;

@Profile("!test")
public interface UserPersistentRepository {
    User findByEmail(String email);

    User findByIdAndUuid(long id, String uuid);

    User insert(User user);

    User replace(long id, User user);

    boolean removeById(long id);
}
