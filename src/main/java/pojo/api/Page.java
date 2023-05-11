package pojo.api;

import lombok.Data;

@Data
public class Page {

    private int number;
    private int size;
    private int totalElements;
    private int totalPages;
}
