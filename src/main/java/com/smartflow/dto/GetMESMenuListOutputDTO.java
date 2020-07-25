package com.smartflow.dto;

public class GetMESMenuListOutputDTO {
    private Integer id;
    private Integer bpid;
    private Integer mpid;
    private String icon;
    private String name;
    private String route;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBpid() {
        return bpid;
    }

    public void setBpid(Integer bpid) {
        this.bpid = bpid;
    }

    public Integer getMpid() {
        return mpid;
    }

    public void setMpid(Integer mpid) {
        this.mpid = mpid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
