package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class LocalUserPersistentRepository implements UserPersistentRepository {
  private final List<User> users = Collections.synchronizedList(new ArrayList<>());

  @Override
  public User findByEmail(String email) {
    try {
      var user = this.users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
      return user.get();
    } catch (NullPointerException | NoSuchElementException ignored) {
      return null;
    }
  }

  @Override
  public User findByIdAndUuid(long id, String uuid) {
    try {
      var user = this.users.stream().filter(u -> u.getId() == id && u.getUuid().equals(uuid)).findFirst();
      return user.get();
    } catch (NullPointerException | NoSuchElementException ignored) {
      return null;
    }
  }

  @Override
  public User insert(User user) {
    this.users.add(user);
    try {
      return Util.deepClone(user);
    } catch (IOException | ClassNotFoundException ignored) {
      return null;
    }
  }

  @Override
  public User replace(long id, User user) {
    Integer removeIndex = null;
    for (int i = 0; i < this.users.size(); i++) {
      if (this.users.get(i).getId() != id) {
        continue;
      }
      removeIndex = i;
      break;
    }
    if (removeIndex == null) {
      throw new RuntimeException("Data does not exist.");
    }

    this.users.set(removeIndex, user);
    return null;
  }

  @Override
  public boolean removeById(long id) {
    Integer removeIndex = null;
    for (int i = 0; i < this.users.size(); i++) {
      if (this.users.get(i).getId() != id) {
        continue;
      }
      removeIndex = i;
      break;
    }
    if (removeIndex == null) {
      return true;
    }

    this.users.remove((int)removeIndex);
    return true;
  }
}
