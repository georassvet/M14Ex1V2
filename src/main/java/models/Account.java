package main.java.models;

public class Account {
    private long id;
    private String description;

    public Account(long id, String description) {
        this.id=id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return id+","+description;
    }
}
