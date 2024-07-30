package org.leocoder.codehub.web.model.vo.setting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-30 09:30
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsDetailRspVO {
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String csdnHomepage;
    private String giteeHomepage;
    private String zhihuHomepage;
}
