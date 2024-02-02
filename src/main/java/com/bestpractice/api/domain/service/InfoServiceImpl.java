package com.bestpractice.api.domain.service;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.common.exception.InternalServerError;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    private final InfoPersistentRepository infoRepository;

    public InfoServiceImpl(InfoPersistentRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public List<InfoResponse> getInfos() {
        List<Info> infoEntities = new ArrayList<>();
        try {
            infoEntities = this.infoRepository.findAll();
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        List<InfoResponse> res = new ArrayList<>();
        for (Info i : infoEntities) {
            res.add(new InfoResponse(i.getId(), i.getTitle(), i.getDescription()));
        }
        return res;
    }

    @Override
    public InfoResponse getInfo(Long id) {
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse updateInfo(Long id, InfoRequest req) {
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new BadRequest();
        }

        info = req.convert(info.getId());
        try {
            info = this.infoRepository.save(info);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse generateInfo(InfoRequest request) {
        Info info;
        try {
            info = this.infoRepository.save(request.convert());
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public void deleteInfo(Long id) {
        try {
            this.infoRepository.removeById(id);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
    }
}
