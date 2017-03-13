package ru.rustem.models;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    @Column(name = "message")
    private String message;
    @Column(name = "picture")
    private String picture;

    public Article() {
    }

    public Article(Builder builder) {
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

    public Integer getId() {
        return id;
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

        public Article build() {
            return new Article(this);
        }
    }
}
