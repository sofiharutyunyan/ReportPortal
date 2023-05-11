package pojo.api;

import lombok.Data;

import java.util.List;

@Data
public class Dashboards {
    private List<Content> content;
    private Page page;
}
