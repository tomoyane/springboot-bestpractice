package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import java.util.List;

public interface InfoService {
  List<InfoResponse> getInfos();
  InfoResponse getInfo(String id);
  InfoResponse updateInfo(String id, InfoRequest req);
  InfoResponse generateInfo(InfoRequest request);
  void deleteInfo(String id);
}
