package com.bestpractice.api.app.v1;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/infos")
public class RdbmsController {

    private final InfoServiceImpl infoService;

    public RdbmsController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @GetMapping()
    public List<InfoResponse> getInfos() {
        return this.infoService.getInfos();
    }

    @GetMapping(value="/{id}")
    public InfoResponse getInfo(@PathVariable("id") Long id) {
        return this.infoService.getInfo(id);
    }

    @PostMapping
    public ResponseEntity<InfoResponse> postInfo(
        @RequestBody InfoRequest req)
        throws URISyntaxException {

        InfoResponse res = this.infoService.generateInfo(req);
        return ResponseEntity
            .created(new URI("/api/v1/infos/" + res.getId()))
            .body(res);
    }

    @PutMapping(value="/{id}")
    public InfoResponse putInfo(
        @PathVariable("id") Long id,
        @RequestBody InfoRequest req) {

        return this.infoService.updateInfo(id, req);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteInfo(@PathVariable("id") Long id) {
        this.infoService.deleteInfo(id);
        return Collections.singletonMap("message", "ok");
    }
}