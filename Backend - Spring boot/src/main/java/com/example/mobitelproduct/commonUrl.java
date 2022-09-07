package com.example.mobitelproduct;

public class commonUrl {
    public static final String productList = "/product";
    public static final String getALLWithFilters = productList + "/search/page";
    public static final String findAllHisPage = "/history/page";

    public static final String getAll = productList + "/all";
    public static final String create = productList + "/add";
    public static final String update = productList + "/update";
    public static final String delete = productList + "/delete/{id}";
}
