package spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto {

    private String title;
    private String date;
    private String message;
    private String picture;

    public ArticleDto() {
    }

    public ArticleDto(Builder builder) {
        this.title = builder.title;
        this.date = builder.date;
        this.message = builder.message;
        this.picture = builder.picture;
    }


    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getPicture() {
        return picture;
    }

    public static class Builder {

        private String title;
        private String date;
        private String message;
        private String picture;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder picture(String picture) {
            this.picture = picture;
            return this;
        }

        public ArticleDto build() {
            return new ArticleDto(this);
        }
    }
}
