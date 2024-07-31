package org.leocoder.codehub.web.markdown.provider;

import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-31 09:19
 * @description :
 */
public class NofollowLinkAttributeProvider implements AttributeProvider {

    /**
     * 网站域名（上线后需要改成自己的域名）
     */
    private final static String DOMAIN = "www.xxxxxxxxxx.com";

    @Override
    public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
        if (node instanceof Link) {
            Link linkNode = (Link) node;
            // 获取链接地址
            String href = linkNode.getDestination();
            // 如果链接不是自己域名，则添加 rel="nofollow" 属性
            if (!href.contains(DOMAIN)) {
                attributes.put("rel", "nofollow");
            }
        }
    }
}