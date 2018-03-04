package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.service.RdbmsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/")
public class RdbmsController {

    private final RdbmsService rdbmsService;

    public RdbmsController(RdbmsService rdbmsService) {
        this.rdbmsService = rdbmsService;
    }

    @GetMapping()
    public Map<String, List<UserEntity>> getUserList() {
        Map<String, List<UserEntity>> map = new HashMap<>();
        map.put("users", rdbmsService.getUserList());
        return map;
    }

    @GetMapping(value="{id}")
    public Map<String, UserEntity> getUser(@PathVariable("id") Long id) {
        Map<String, UserEntity> map = new HashMap<>();
        map.put("user", rdbmsService.getUser(id));
        return map;
    }

    @PostMapping
    public Map<String, UserEntity> postUser(@RequestBody UserEntity userEntity) {
        rdbmsService.generateUser(userEntity);
        Map<String, UserEntity> map = new HashMap<>();
        map.put("user", userEntity);
        return map;
    }

    @PutMapping
    public Map<String, UserEntity> putUser(@RequestBody UserEntity userEntity) {
        rdbmsService.generateUser(userEntity);
        Map<String, UserEntity> map = new HashMap<>();
        map.put("user", userEntity);
        return map;
    }

    @DeleteMapping(value = "{id}")
    public Map<String, String> deleteUser(@PathVariable("id") Long id) {
        rdbmsService.deleteUser(id);
        Map<String, String> map = new HashMap<>();
        map.put("user", "success.");
        return map;
    }
}