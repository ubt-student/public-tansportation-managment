package org.pbm.map.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "map")
@RequiredArgsConstructor
public class TestApi {

    @GetMapping("test")
    public String getTest(){
        return "test";
    }
}
