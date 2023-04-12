package configuration.constants;

public interface Endpoints {

    String CREATE_DASHBOARD = "/dashboard/%s";
    String UPDATE_DASHBOARD = "";
    String DELETE_DASHBOARD = "";
    String GET_DASHBOARD_BY_ID = "/dashboard/%s";
    String ADD_WIDGET_TO_DASHBOARD = "/dashboard/%s/add";
    String CREATE_WIDGET = "/widget";
    String GET_ALL_SHARED_DASHBOARDS = "/dashboard/shared";
}
