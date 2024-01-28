package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import java.util.List;

public interface InfoService {
  List<InfoResponse> getInfos();
  InfoResponse getInfo(Long id);
  InfoResponse updateInfo(Long id, InfoRequest req);
  InfoResponse generateInfo(InfoRequest request);
  void deleteInfo(Long id);
}
