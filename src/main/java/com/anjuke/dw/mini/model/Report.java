package com.anjuke.dw.mini.model;

public class Report {
    private Integer id;
    private String name;
    private String ownerName;
    private String receivers;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getReceivers() {
        return receivers;
    }
    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }
}
