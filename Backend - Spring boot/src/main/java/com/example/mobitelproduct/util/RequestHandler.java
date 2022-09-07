package com.example.mobitelproduct.util;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestHandler<T> {

    public RequestHeader requestHeader;
    private Map<String, T> paraMap;
    private List<T> paraList;
    private T body;
    private Long pageSize;
    private Long pageNumber;
}
