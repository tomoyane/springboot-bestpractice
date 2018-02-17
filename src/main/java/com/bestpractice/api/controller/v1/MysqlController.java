package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.Users;
import com.bestpractice.api.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/mysql/users/")
public class MysqlController {

    @Autowired
    MysqlService mysqlService;

    @ResponseBody
    @GetMapping(value="{id}")
    public Map<String, Users> getUser(@PathVariable("id") Long id) {
        Map<String, Users> map = new HashMap<>();
        map.put("users", mysqlService.getUser(id));
        return map;
    }
}