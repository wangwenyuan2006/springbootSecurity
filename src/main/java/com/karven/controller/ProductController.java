package com.karven.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController {

    @RequestMapping("add")
    public String add(){
        return "product/add";
    }

    @RequestMapping("update")
    public String update(){
        return "product/update";
    }

    @RequestMapping("list")
    public String list(){
        return "product/list";
    }

    @RequestMapping("delete")
    public String delete(){
        return "product/delete";
    }

    @Secured("ROLE_ADD_PRODUCT")
    @RequestMapping("annoSecurity")
    public String annoSecurity(){
        return "product/annoSecurity";
    }

}
