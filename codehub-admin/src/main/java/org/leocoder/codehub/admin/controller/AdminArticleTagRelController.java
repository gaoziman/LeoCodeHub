package org.leocoder.codehub.admin.controller;

import org.leocoder.codehub.admin.service.AdminArticleTagRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-27 01:50
 * @description : Admin 标签模块
 */
@RestController
@RequestMapping("/admin/article_tag_rel")
public class AdminArticleTagRelController {

    @Autowired
    private AdminArticleTagRelService adminArticleTagRelService;

}
