package com.example.Swapyard.models;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Items() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String clothingCondition;
    //choose from checkbox
    private int size;
    //choose from checkbox
    private String color;
    //choose from checkbox
    private String material;

    //Make it one to many
    @OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Post images;

    public String getClothingCondition() {
        return clothingCondition;
    }

    public void setClothingCondition(String clothingCondition) {
        this.clothingCondition = clothingCondition;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Post getImages() {
        return images;
    }

    public void setImages(Post images) {
        this.images = images;
    }
}
