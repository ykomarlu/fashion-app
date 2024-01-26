package com.example.ProductSpringWeb;

import jakarta.persistence.*;

//Thing.java: The purpose of this class is to declare an instance of what is stored instances of the database. This class
//needs to exist because in other files within my program we reference Thing instances such as in ProductDB so to be able
//to write queries that reference a thing's outfitelements an instance variable of that name must exist within the Thing class.

//@Entity designates Thing.java as a class that will convert the object that is stored in the rows of the db we are connected
//to into a java object

@Entity
@Table(name = "thing")
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imageURL;
    private String outfitelements;

    public Thing(String imageURL, String outfitelements) {
        this.imageURL = imageURL;
        this.outfitelements = outfitelements;
    }

    public Thing() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getoutfitelements() {
        return outfitelements;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setoutfitelements(String outfitelements) { this.outfitelements = outfitelements;
    }

    public String toString () {
        return "imageURL: " + (this.imageURL) + " outfitElements: " + (this.outfitelements);
    }

}
