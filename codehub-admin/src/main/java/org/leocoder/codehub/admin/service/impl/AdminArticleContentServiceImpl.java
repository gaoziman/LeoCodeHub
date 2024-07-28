package org.leocoder.codehub.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.codehub.common.model.domain.ArticleContent;
import org.leocoder.codehub.common.mapper.ArticleContentMapper;
import org.leocoder.codehub.admin.service.AdminArticleContentService;
/**
 * @author : Leo
 * @date  2024-07-27 17:16
 * @version 1.0
 * @description :
 */

@Service
public class AdminArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements AdminArticleContentService {

}
