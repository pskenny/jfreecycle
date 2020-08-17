package io.github.pskenny.jfreecycle;

import java.util.Date;

import lombok.Data;

@Data class Post {
    public enum Type {WANTED, OFFER}

    private Type type;
    private Date date;
    private String title;
    private String location;
    private long id = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(" ");
        sb.append(date);
        sb.append(" ");
        sb.append(title);
        sb.append(" ");
        sb.append(location);
        sb.append(" ");
        sb.append(id);

        return sb.toString();
    }

}