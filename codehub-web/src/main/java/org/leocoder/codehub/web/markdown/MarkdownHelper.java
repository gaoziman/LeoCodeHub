package org.leocoder.codehub.web.markdown;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.leocoder.codehub.web.markdown.provider.NofollowLinkAttributeProvider;
import org.leocoder.codehub.web.markdown.renderer.ImageNodeRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-31 09:08
 * @description : Markdown 工具类
 */
public class MarkdownHelper {

    /**
     * 初始化
     */
    static {
        // Markdown 拓展
        List<Extension> extensions = Arrays.asList(
                TablesExtension.create(),
                // 表格拓展
                HeadingAnchorExtension.create(),
                // 标题锚定项
                ImageAttributesExtension.create(),
                // 图片宽高
                TaskListItemsExtension.create()
                // 任务列表
        );

        PARSER = Parser.builder().extensions(extensions).build();
        HTML_RENDERER = HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(context -> new NofollowLinkAttributeProvider())
                .nodeRendererFactory(context -> new ImageNodeRenderer(context))
                .build();
    }

    /**
     * Markdown 解析器
     */
    private final static Parser PARSER;
    /**
     * HTML 渲染器
     */
    private final static HtmlRenderer HTML_RENDERER;


    /**
     * 将 Markdown 转换成 HTML
     * @param markdown
     * @return
     */
    public static String convertMarkdown2Html(String markdown) {
        Node document = PARSER.parse(markdown);
        return HTML_RENDERER.render(document);
    }

    public static void main(String[] args) {
        String markdown = "# 一级标题\n" +
                "## 二级标题\n";
        System.out.println(MarkdownHelper.convertMarkdown2Html(markdown));
    }

}
