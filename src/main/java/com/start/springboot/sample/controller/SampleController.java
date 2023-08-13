package com.start.springboot.sample.controller;

import com.start.springboot.sample.dto.Sample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/sayHello")
    public Sample makeSample() {
        Sample svo = new Sample();
        svo.setVal1("안녕");
        svo.setVal2("만나서");
        svo.setVal3("반가워");

        System.out.println(svo);

        return svo;
    }
}


