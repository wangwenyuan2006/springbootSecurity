package com.karven.controller;

import com.karven.entity.member.Member;
import com.karven.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private MemberService memberService;

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

    @GetMapping("insertMember")
    public String insertMember(){
        int id = memberService.insert(new Member("zhangsan"));
        return "受影响的行："+id;
    }
}
