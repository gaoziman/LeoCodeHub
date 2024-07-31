package org.leocoder.codehub.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-31 14:43
 * @description :
 */

@Getter
public class ReadArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public ReadArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}