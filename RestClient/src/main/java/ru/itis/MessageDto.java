package ru.itis;


public class MessageDto {
    private String message;
    private String from;

    public MessageDto(Builder builder) {
        this.message = builder.message;
        this.from = builder.from;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public static class Builder {
        private String message;
        private String from;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }
}
