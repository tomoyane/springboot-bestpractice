package com.bestpractice.api.infrastrucuture.persistent.local;

import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class LocalInfoPersistentRepository implements InfoPersistentRepository {
  private final List<Info> infos = Collections.synchronizedList(new ArrayList<>());

  @Override
  public List<Info> findAll() {
    return this.infos;
  }

  @Override
  public Info findById(Long id) {
    try {
      var info = this.infos.stream().filter(u -> u.getId().equals(id)).findFirst();
      return info.get();
    } catch (NullPointerException | NoSuchElementException ignored) {
      return null;
    }
  }

  @Override
  public Info save(Info info) {
    this.infos.add(info);
    try {
      return Util.deepClone(info);
    } catch (IOException | ClassNotFoundException ignored) {
      return null;
    }
  }

  @Override
  public boolean removeById(Long id) {
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

    this.infos.remove((int)removeIndex);
    return true;
  }
}
