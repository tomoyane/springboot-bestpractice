package com.bestpractice.api.app.v1;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public InfoResponse getInfo(@PathVariable("id") String id) {
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
        @PathVariable("id") String id,
        @RequestBody InfoRequest req) {

        return this.infoService.updateInfo(id, req);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteInfo(@PathVariable("id") String id) {
        this.infoService.deleteInfo(id);
        return Collections.singletonMap("message", "ok");
    }
}