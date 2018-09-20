package com._3u.controller;

import com._3u.mapper.TUseropinion;
import com._3u.service.TUseropinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/opinion")
public class TUserOpinionController {

    @Autowired
    TUseropinionService tUseropinionService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TUseropinion> list() {
        return tUseropinionService.findAllOpinion();
    }
}
