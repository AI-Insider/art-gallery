/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.LinkedList;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Simeon
 */
@RestController
public class RestRequests {
    MongoClientURI uri = new MongoClientURI(
    "mongodb+srv://default:mongodefault@java-artgallery-zrorg.gcp.mongodb.net/test?retryWrites=true");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("art-gallery");
    MongoCollection<Document> collection = database.getCollection("Art");
     @RequestMapping(value = "/art", method = RequestMethod.GET, produces = "application/json")
    public String getAllArt() {
      LinkedList<String> json = new LinkedList<String>();
      Block<Document> jsonBlock = new Block<Document>() {
       @Override
       public void apply(final Document document) {
           
           json.add(document.toJson());
       }
    };
       collection.find(new Document()).forEach(jsonBlock);
       
       return json.toString();
    }
}
