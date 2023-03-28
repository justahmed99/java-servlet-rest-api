package com.justahmed99.rest.demo.entity;

public class Items {
    private String id;
    private String name;
    private Integer itemNumber;
    private Long price;

    private Boolean isActive;

    public Items() {
    }

    public Items(String id, String name, Integer itemNumber, Long price) {
        this.id = id;
        this.name = name;
        this.itemNumber = itemNumber;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
