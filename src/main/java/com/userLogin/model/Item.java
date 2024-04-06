package com.userLogin.model;

public class Item {
    private Integer id;
    private String name;
    private String description;
    private String smallUrlPicture;
    private String bigUrlPicture;
    private Integer inStock;
    private Float price;
    private Boolean isFavorite;
    public Item(Integer id, String name, String description, String smallUrlPicture, String bigUrlPicture, Integer inStock, Float price, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.smallUrlPicture = smallUrlPicture;
        this.bigUrlPicture = bigUrlPicture;
        this.inStock = inStock;
        this.price = price;
        this.isFavorite = false;
    }
    public Item(Integer id, String name , String smallUrlPicture, Float price, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.smallUrlPicture = smallUrlPicture;
        this.price = price;
        this.isFavorite = false;
    }
    public Item() {}
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getSmallUrlPicture() {
        return smallUrlPicture;
    }
    public String getBigUrlPicture() {
        return bigUrlPicture;
    }
    public Integer getInStock() {
        return inStock;
    }
    public Float getPrice() {
        return price;
    }
    public Boolean getIsFavorite() {
        return this.isFavorite;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSmallUrlPicture(String urlPicture) {
        this.smallUrlPicture = urlPicture;
    }
    public void setBigUrlPicture(String bigUrlPicture) {
        this.bigUrlPicture = bigUrlPicture;
    }
    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
    public Item semiItem() {
        return new Item(
                this.id,
                null,
                this.name,
                this.smallUrlPicture,
                null,
                this.inStock,
                this.price,
                this.isFavorite
        );
    }
}
