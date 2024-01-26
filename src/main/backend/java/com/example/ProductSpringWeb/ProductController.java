package com.example.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//AHH so we do cross-origin things in the backend because without it the backend won't be able to send data to another url
//besides the current one. So now with this header this backend knows that it can send requests properly to any url and won't
//send null data
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
//All api urls should be prepended with /api/v1/ this you do using requestmapping
//Use this for version control and monitoring the versions of the api
public class ProductController {

    @Autowired
    ThingService service;

    //you need to provide the annotation @PathVariable to tell Springboot that
    //the variable name in the url path is the same as the one you're referring
    //to in the paramaters.

//    This mapping retrieves the 3 elements passed in from the browser and then passes them as params to getThing method
//    located in ThingService.java
    @GetMapping("/Thing/{elemOne}/{elemTwo}/{elemThree}")
    public List<Thing> getProduct(@PathVariable String elemOne, @PathVariable String elemTwo, @PathVariable String elemThree) {
        return service.getThing(elemOne, elemTwo, elemThree);
    }

//    @PostMapping("/Thing")
//    //RequestBody annotation basically takes the data values from the JSON that was used to make the post request
//    //and create a Thing object out of it
//    //ALso I should have noticed that in the insert statement that was being made the values that the PreparedStatement
//    //was receiving was all nulls even though I had data which shows that Spring was making a completely seperate null object
//    //and trying to add that to the database
//    public void addProduct(@RequestBody Thing t) {
//            service.add(t);
//    }

}
