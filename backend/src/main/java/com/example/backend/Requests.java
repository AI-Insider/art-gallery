/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.IOException;


import org.bson.Document;
import org.bson.types.ObjectId;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Simeon
 */

	
@Controller
public class Requests {
   
    MongoClientURI uri = new MongoClientURI(
    "mongodb+srv://default:mongodefault@java-artgallery-zrorg.gcp.mongodb.net/test?retryWrites=true");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("art-gallery");
    MongoCollection<Document> collection = database.getCollection("Art");
 
    
   @PostMapping("/")
   public String addArt (@RequestParam("uploadFile") MultipartFile uploadedFile, @RequestParam("name") String name) throws IOException{
        

           
         Art art = new Art(name, uploadedFile.getOriginalFilename(),ObjectId.get());
         File file = new File(art.fileName);
         uploadedFile.transferTo(file);
         
         Document document = new Document("name", art.name).append("_id",art.get_id()).append("filename",art.fileName);
         collection.insertOne(document);
         
         return "redirect:index.html";
      
         
        
        
   }
   @GetMapping("/")
   public String sendPage(){
       return "index.html";
   }
  
  
   
  
   
   
   
   
}

