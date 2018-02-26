package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.Users;
import com.bestpractice.api.service.MysqlService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mysql/users/")
public class MysqlController {

    private final MysqlService mysqlService;

    public MysqlController(MysqlService mysqlService) {
        this.mysqlService = mysqlService;
    }

    @GetMapping(value="{id}")
    public Map<String, Users> getUser(@PathVariable("id") Long id) {
        Map<String, Users> map = new HashMap<>();
        map.put("users", mysqlService.getUser(id));
        return map;
    }

    @PostMapping
    public Map<String, Users> postUser(@RequestBody Users users) {
        Map<String, Users> map = new HashMap<>();
        map.put("users", users);
        return map;
    }

}