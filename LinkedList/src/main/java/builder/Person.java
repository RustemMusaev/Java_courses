package builder;


public class Person {

    private Integer id;
    private String name;

    public Person(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Integer id;
        private String name;

        public Builder id(Integer id){
            this.id = id;
            return this;
        }

        public Builder name(String name){

            if (name.equals("hello")) {
                System.out.println(name);
            }
            this.name = name;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
