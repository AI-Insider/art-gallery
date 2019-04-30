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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Simeon
 */
@RestController
public class RestRequests {
    MongoClientURI uri = new MongoClientURI(
    "");

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
     @RequestMapping(value="/images/", method=RequestMethod.GET, params="filename")
   public ResponseEntity sendImage(@RequestParam("filename") String fileName) throws IOException{
       String fullName = System.getProperty("user.dir")+"/uploads/"+fileName;
       InputStream inputStream = new FileInputStream(fullName);
       return ResponseEntity
                .ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(inputStream));
   }
}
