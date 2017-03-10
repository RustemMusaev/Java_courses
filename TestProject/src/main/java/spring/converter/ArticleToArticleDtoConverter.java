package spring.converter;

import spring.dto.ArticleDto;
import spring.models.Article;

public class ArticleToArticleDtoConverter {
    public static ArticleDto convertArticleDtoWithoutId(Article article) {
        return new ArticleDto.Builder()
                .title(article.getTitle())
                .date(article.getDate())
                .message(article.getMessage())
                .picture(article.getPicture())
                .build();
    }
}
