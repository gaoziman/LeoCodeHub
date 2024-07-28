package org.leocoder.codehub.admin.controller;

import org.leocoder.codehub.admin.service.AdminArticleCategoryRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 文章所属分类关联表(t_article_category_rel)表控制层
*
* @author 程序员Leo
*/
@RestController
@RequestMapping("/t_article_category_rel")
public class AdminArticleCategoryRelController {

    @Autowired
    private AdminArticleCategoryRelService articleCategoryRelService;



}
