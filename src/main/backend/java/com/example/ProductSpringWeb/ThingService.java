package com.example.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class ThingService {
    //Use postgres database instead of the list

    //ThingService.java: The purpose of this class is store all the methods that the controller calls. The methods in this
    // class will consist of those that will call methods in the ProductDB.class and some that will consist of an actual
    // implementation that won't involve the steps of retrieving data from db

    @Autowired
// Annotation that pairs with @Bean by assigning the object instance of ProductDB class to the db object that is created
// through springboot dependency injection
    ProductDB db;


    //This method is responsible for calling a method of the ProductDB class and it should retrieve a list of Things and
    //return it
    public List<Thing> getThing (String elemOne, String elemTwo, String elemThree) {
        return db.findByOutfit(elemOne, elemTwo, elemThree);
    }

}
