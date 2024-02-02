package com.bestpractice.api.infrastrucuture.persistent;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("!test")
public interface InfoPersistentRepository {
  List<Info> findAll();

  Info findById(Long id);

  Info insert(Info info);

  Info replace(long id, Info info);

  boolean removeById(Long id);
}
