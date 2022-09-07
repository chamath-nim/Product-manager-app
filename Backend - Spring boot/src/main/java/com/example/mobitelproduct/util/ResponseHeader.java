package com.example.mobitelproduct.util;

import lombok.Data;
@Data
public class ResponseHeader {
    private String responseCode;
    private String responseDescription;
    private String extraDescription;
}
