package com.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 5;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer masterId;
    private String status;
}
