package org.leocoder.codehub.admin.controller;

import org.leocoder.codehub.admin.service.AdminArticleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 文章内容表(t_article_content)表控制层
*
* @author 程序员Leo
*/
@RestController
@RequestMapping("/t_article_content")
public class AdminArticleContentController {

    @Autowired
    private AdminArticleContentService articleContentService;



}
