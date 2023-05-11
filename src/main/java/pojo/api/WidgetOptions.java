package pojo.api;

import lombok.Data;

@Data
public class WidgetOptions {

      private boolean zoom;
      private String timeline;
      private String viewMode;
      private boolean latest;
      private boolean includeMethods;
      private String launchNameFilter;
}
