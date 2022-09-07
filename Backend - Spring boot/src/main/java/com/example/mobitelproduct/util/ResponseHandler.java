package com.example.mobitelproduct.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResponseHandler<T> {
    private ResponseHeader responseHeader;
    private List<T> paraList;
    private T body;
    private Long count;
    private Page<T> page;
}
