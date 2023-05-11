package pojo.api;

import lombok.Data;

@Data
public class Content {

    private String description;
    private int id;
    private String name;
    private boolean share;
    private String owner;
    private Widget[] widgets;
    private int widgetId;
}
