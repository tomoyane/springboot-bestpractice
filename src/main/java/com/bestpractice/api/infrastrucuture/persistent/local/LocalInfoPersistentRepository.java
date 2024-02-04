package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class LocalInfoPersistentRepository implements InfoPersistentRepository {

  private final List<Info> infos = Collections.synchronizedList(new ArrayList<>());

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Info> findAll() {
    return this.infos;
  }

  @Override
  public Info findById(String id) {
    try {
      var info = this.infos.stream().filter(u -> u.getId().equals(id)).findFirst();
      return info.get();
    } catch (NullPointerException | NoSuchElementException ignored) {
      return null;
    }
  }

  @Override
  public Info insert(Info info) {
    this.infos.add(info);
    return info;
  }

  @Override
  public Info replace(String id, Info info) {
    Integer removeIndex = null;
    for (int i = 0; i < this.infos.size(); i++) {
      if (this.infos.get(i).getId().equals(id)) {
        continue;
      }
      removeIndex = i;
      break;
    }
    if (removeIndex == null) {
      throw new RuntimeException("Data does not exist.");
    }

    this.infos.set(removeIndex, info);
    return null;
  }

  @Override
  public boolean removeById(String id) {
    Integer removeIndex = null;
    for (int i = 0; i < this.infos.size(); i++) {
      if (!this.infos.get(i).getId().equals(id)) {
        continue;
      }
      removeIndex = i;
      break;
    }
    if (removeIndex == null) {
      return true;
    }

    this.infos.remove((int) removeIndex);
    return true;
  }
}
