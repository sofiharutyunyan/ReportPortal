package pojo.api;

import lombok.Data;

@Data
public class Widget {

    private String widgetName;
    private int widgetId;
    private String widgetType;
    private WidgetSize widgetSize;
    private WidgetPosition widgetPosition;
    private boolean share;
    private WidgetOptions widgetOptions;

}
