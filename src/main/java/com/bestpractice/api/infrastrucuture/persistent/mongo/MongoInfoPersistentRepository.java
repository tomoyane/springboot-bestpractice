package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.List;

public class MongoInfoPersistentRepository implements InfoPersistentRepository {

  @Override
  public List<Info> findAll() {
    return null;
  }

  @Override
  public Info findById(String id) {
    return null;
  }

  @Override
  public Info insert(Info info) {
    return null;
  }

  @Override
  public Info replace(String id, Info info) {
    return null;
  }

  @Override
  public boolean removeById(String id) {
    return false;
  }
}
