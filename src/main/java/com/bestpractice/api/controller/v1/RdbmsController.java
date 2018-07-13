package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.Info;
import com.bestpractice.api.domain.service.InfoService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public Map<String, List<Info>> getInfos() {
        return Collections.singletonMap("infos", this.infoService.getInfoList());
    }

    @GetMapping(value="/{id}")
    public Map<String, Info> getInfo(@PathVariable("id") Long id) {
        return Collections.singletonMap("info", this.infoService.getInfo(id));
    }

    @PostMapping
    public Map<String, Info> postInfo(@RequestBody Info info) {
        this.infoService.generateInfo(info);
        return Collections.singletonMap("info", info);
    }

    @PutMapping
    public Map<String, Info> putInfo(@RequestBody Info info) {
        this.infoService.generateInfo(info);
        return Collections.singletonMap("info", info);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteInfo(@PathVariable("id") Long id) {
        this.infoService.deleteInfo(id);
        return Collections.singletonMap("message", "success deleted.");
    }
}