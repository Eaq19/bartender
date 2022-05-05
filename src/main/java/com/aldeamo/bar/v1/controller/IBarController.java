package com.aldeamo.bar.v1.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bar")
public interface IBarController {

    @GetMapping(value="/distribute")
    List<Integer> getDistribute(@RequestParam int iterations, @RequestParam Long id);

    @GetMapping(value="/ping")
    Boolean getPing();
}
