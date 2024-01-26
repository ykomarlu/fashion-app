package com.example.ProductSpringWeb;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDB extends JpaRepository<Thing, Integer>{
    //if you want to create a method to query by a particular column/columns, data jpa,
    //allows you to easily create a method by typing in the method signature in the database layer like this
    //Name of the method MUST BE findBy_____();
    //code out the method so it looks for the string in the outfitElem column and returns a list of Things
    //to the thingService class

    //If you want to query from a particular column but have a specific query then above the signature
    //with the Query annotation specify the sql query you want the function to execute

    //Make this query where it returns all the results that contain all three params as substrings
//    @Query("SELECT d.imageURL, d.outfitElements FROM Thing d WHERE LOWER(d.outfitElements) LIKE %?1% OR " +
//            "LOWER(d.outfitElements) LIKE %?1 OR LOWER(d.outfitElements) LIKE ?1% OR " +
//            "LOWER(d.outfitElements) LIKE %?2% OR LOWER(d.outfitElements) LIKE ?2% OR " +
//            "LOWER(d.outfitElements) LIKE %?2 OR LOWER(d.outfitElements) LIKE %?3% OR " +
//            "LOWER(d.outfitElements) LIKE ?3% OR LOWER(d.outfitElements) LIKE %?3")

//    @Query("select new com.example.ProductSpringWeb.Thing(a.imageURL, a.outfitelements) from Thing a WHERE LOWER(a.outfitelements) LIKE %?1% OR " +
//            "LOWER(a.outfitelements) LIKE %?2% OR LOWER(a.outfitelements) LIKE %?3%")
//    List<Thing> findByOutfit(String elemOne, String elemTwo, String elemThree);


//    Purpose of this query: Currently, it returns the thing objects that contain the three different params in the outfitelements
//    The actual purpose of the query should be to return the objects that contain all 3 params before the ones that only
//    contain two and before those that only contain 1.

//    I need a way to measure how many params each thing object contains and then sort it based off that.

//    Maybe an integer instance variable within Thing object that could increment each time a "LOWER(a.outfitelements) LIKE '%?x%' is true

//    

    @Query(value = "WITH tempOutfit AS (" +
            "select a.id, a.imageurl, a.outfitelements" +
            ",(case when Lower(a.outfitelements) LIKE '%jeans%' THEN 1 ELSE 0 END +" +
            "case when LOWER(a.outfitelements) LIKE '%sweater%' THEN 1 ELSE 0 END +" +
            "case when LOWER(a.outfitelements) LIKE '%shirt%' THEN 1 ELSE 0 END) AS ordercolumn" +
            " from Thing a" +
            " ORDER BY ordercolumn DESC" +
            ")" +
            "SELECT * FROM tempOutfit" +
            " WHERE ordercolumn > 0",
            nativeQuery = true)

//  CCreation of a sortcolumn that has one assigned to it if either any of the params is in outfit elements else 2 is put in
//            that column

        //create custom query but has to be native because providing arg in query annotation. Because jpa can't construct this specific query automatically
    List<Thing> findByOutfit(String elemOne, String elemTwo, String elemThree);


    //and the other search results
//    @Query("SELECT d FROM 'Thing' d WHERE LOWER(outfitElements) LIKE %?1% OR " +
//            "LOWER(outfitElements) LIKE %?1 OR LOWER(outfitElements) LIKE %?1 OR " +
//            "LOWER(outfitElements) LIKE %?2% OR LOWER(outfitElements) LIKE ?2% OR " +
//            "LOWER(outfitElements) LIKE %?2 OR LOWER(outfitElements) LIKE %?3% OR " +
//            "LOWER(outfitElements) LIKE ?3% OR LOWER(outfitElements) LIKE %?3")
//    List<Thing> outfitContainsName(String elemOne, String elemTwo, String elemThree);
//public class ProductDB {
//    Connection conn = null;
//
//    public ProductDB() {
//        try {
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=public",
//            "postgres", "Incantat3m");
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Thing> findOutfit(String elemOne, String elemTwo, String elemThree) {
//        String query = "Select * From \"Thing\" Where Lower(outfitElements) Like \'%elemOne%\' Or Lower(outfitElements) " +
//                "Like \'elemOne%\' Or Lower(outfitElements) Like \'%elemOne\' Or Lower(outfitElements) Like \'%elemOne%\' Or Lower(outfitElements) \" +\n" +
//                "Like \'elemOne%\' Or Lower(outfitElements) Like \'%elemOne\'";
//        try {
//            PreparedStatement st = conn.prepareStatement(query);
//            //execute() vs executeQuery(): the first doesn't return data but the other does
//            ResultSet rs = st.executeQuery();
//            List<Thing> list = new ArrayList<Thing>();
//            //here in this while loop we are converting the result objects to Thing objects
//            //What we have to do to convert it is create a new Thing object by manually constructing the object (like the constructor
//            //would do) and then add it to a list
//            while (rs.next()) {
//                Thing t = new Thing();
//                t.setImageURL(rs.getString(1));
//                t.setOutfitElements(rs.getString(2));
//                list.add(t);
//            }
//
//            return list;
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
