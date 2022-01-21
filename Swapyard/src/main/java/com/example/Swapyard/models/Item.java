package com.example.Swapyard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //choose from checkbox
    private int size;
    //choose from checkbox
    private String color;
    //choose from checkbox
    private String material;
    //choose from checkbox
    private String condition;

    //The item has all off these attributes that are not null, especially images
    @OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List <Post> images;

    public List<Post> getImages() {
        return images;
    }

    public void setImages(List<Post> images) {
        this.images = images;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
