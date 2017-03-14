package ru.rustem.converter;


import ru.rustem.dto.ArticleDto;
import ru.rustem.models.Article;

/**
 * This class contains only method for convert Article to ArticleDto
 */
public class ArticleToArticleDtoConverter {
    /**
     *This static method convert Article to ArticleDto, his create new object ArticleDto,
     * use all fields Article without "id"
     * @param article - object for transformation to ArticleDto
     * @return ArticleDto for use to output for client request
     */
    public static ArticleDto convertArticleDtoWithoutId(Article article) {
        return new ArticleDto.Builder()
                .title(article.getTitle())
                .date(article.getDate())
                .message(article.getMessage())
                .picture(article.getPicture())
                .build();
    }
}
