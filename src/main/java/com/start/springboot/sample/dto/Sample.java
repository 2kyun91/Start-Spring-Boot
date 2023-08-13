package com.start.springboot.sample.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"val3"})
public class Sample {
    private String val1;
    private String val2;
    private String val3;
}
