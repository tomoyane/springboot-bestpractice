package com.bestpractice.api.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @ResponseBody
    @GetMapping(value="/hello")
    public Map<String, String> sample1() {
        return Collections.singletonMap("key", "Hello world.");
    }
}