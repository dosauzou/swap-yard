package com.example.Swapyard.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Items() {

    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "items")
    @JsonManagedReference
    private List<Swipes> swipes;

    public List<Swipes> getSwipes() {
        return swipes;
    }

    public void setSwipes(List<Swipes> swipes) {
        this.swipes = swipes;
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

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Make it one to many
    @OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Post> images;

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


    public List<Post> getImages() {
        return images;
    }

    public void setImages(List<Post> images) {
        this.images = images;
    }

    @ManyToOne(optional = false)
    @JsonBackReference
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
