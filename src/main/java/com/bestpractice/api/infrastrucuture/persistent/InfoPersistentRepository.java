package com.bestpractice.api.infrastrucuture.persistent;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("!test")
public interface InfoPersistentRepository {

  List<Info> findAll();

  Info findById(Long id);

  Info save(Info info);

  boolean removeById(Long id);
}
