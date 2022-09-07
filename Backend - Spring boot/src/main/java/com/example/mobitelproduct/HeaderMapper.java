package com.example.mobitelproduct;

import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.util.ResponseHandler;
import com.example.mobitelproduct.util.ResponseHeader;

public class HeaderMapper {

    public static final String SUCCESS_CODE = "1111";
    public static final String ILLEGALARGUMENTEXCEPTION_CODE = "2222";
    public static final String SUCCESS_MSG = "Success Transaction.";
    public static final String ILLEGALARGUMENTEXCEPTION_MSG = "Unsuccess Transaction.";


    public static void success(ResponseHandler<?> ptResponse, String extraDescription) {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResponseCode(SUCCESS_CODE);
        responseHeader.setResponseDescription(SUCCESS_MSG);
        responseHeader.setExtraDescription(extraDescription);

        ptResponse.setResponseHeader(responseHeader);
    }

    public static void notFoundError(ResponseHandler<?> ptResponse, String extraDescription){
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResponseCode(ILLEGALARGUMENTEXCEPTION_CODE);
        responseHeader.setResponseDescription(ILLEGALARGUMENTEXCEPTION_MSG);
        responseHeader.setExtraDescription(extraDescription);

        ptResponse.setResponseHeader(responseHeader);
    }

}
