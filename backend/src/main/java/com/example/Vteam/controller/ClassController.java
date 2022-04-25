package com.example.Vteam.controller;

import com.example.Vteam.repository.*;
import com.example.Vteam.utils.MyJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.Vteam.utils.MyFunction.getTime;
import static com.example.Vteam.utils.MyFunction.isLoggedIn;

@RestController
@RequestMapping(value = "/class/api")
public class ClassController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/create")
    private MyJson create(HttpServletRequest request,
                          @RequestParam("username") String username,
                          @RequestParam("cname") String cname,
                          @RequestParam("teacherName") String teacherName) {

    }

    @RequestMapping(value = "/edit")
    private MyJson edit(HttpServletRequest request,
                        @RequestParam("cid") String cid,
                        @RequestParam("cname") String cname) {

    }


    @RequestMapping(value = "/enter")
    private MyJson enter(HttpServletRequest request,
                         @RequestParam("username") String username,
                         @RequestParam("command") String command) {

    }

    @RequestMapping(value = "/invite")
    private MyJson invite(HttpServletRequest request,
                          @RequestParam("cid") String cid,
                          @RequestParam("username") String username,
                          @RequestParam("teacherName") String teacherName) {
    }

    @RequestMapping(value = "/dismiss")
    private MyJson dismiss(HttpServletRequest request,
                           @RequestParam("cid") String cid) {

    }

    @RequestMapping(value = "/kick")
    private MyJson kick(HttpServletRequest request,
                        @RequestParam("cid") String cid,
                        @RequestParam("studentUsername") String studentUsername) {

    }

    //    搜索班级
    @RequestMapping(value = "/searchClass")
    private MyJson searchClass(HttpServletRequest request,
                               @RequestParam("command") String command) {

    }

    @RequestMapping(value = "/get/single")
    private MyJson getSingle(HttpServletRequest request,
                             @RequestParam("cid") String cid) {

    }

    @RequestMapping(value = "/get/student")
    private MyJson getStudent(HttpServletRequest request,

    }

    @RequestMapping(value = "/get/all")
    private MyJson getAll(HttpServletRequest request,
                          @RequestParam("teacherUsername") String teacherUsername) {

    }

    @RequestMapping(value = "/search")
    private MyJson search(HttpServletRequest request
//                          @RequestParam("cname") String cname,
//                          @RequestParam("pageIndex") int pageIndex,
//                          @RequestParam("pageSize") int pageSize
    ) {

    }

    @RequestMapping(value = "/info")
    private MyJson info(HttpServletRequest request,
                        @RequestParam("username") String username) {

    }


}
