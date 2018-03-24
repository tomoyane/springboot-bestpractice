package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.InfoEntity;
import com.bestpractice.api.service.InfoService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/infos")
public class RdbmsController {

    private final InfoService infoService;

    public RdbmsController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping()
    public Map<String, List<InfoEntity>> getInfos() {
        Map<String, List<InfoEntity>> map = new HashMap<>();
        map.put("infos", infoService.getInfoList());
        return map;
    }

    @GetMapping(value="/{id}")
    public Map<String, InfoEntity> getInfo(@PathVariable("id") Long id) {
        Map<String, InfoEntity> map = new HashMap<>();
        map.put("info", infoService.getInfo(id));
        return map;
    }

    @PostMapping
    public Map<String, InfoEntity> postInfo(@RequestBody InfoEntity infoEntity) {
        infoService.generateInfo(infoEntity);
        Map<String, InfoEntity> map = new HashMap<>();
        map.put("info", infoEntity);
        return map;
    }

    @PutMapping
    public Map<String, InfoEntity> putInfo(@RequestBody InfoEntity infoEntity) {
        infoService.generateInfo(infoEntity);
        Map<String, InfoEntity> map = new HashMap<>();
        map.put("info", infoEntity);
        return map;
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteInfo(@PathVariable("id") Long id) {
        infoService.deleteInfo(id);
        Map<String, String> map = new HashMap<>();
        map.put("info", "success.");
        return map;
    }
}