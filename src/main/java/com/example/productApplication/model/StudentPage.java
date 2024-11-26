package com.example.productApplication.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class StudentPage {

    private int pageNumber;
    private int pageSize = 3;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "lastName";


}
