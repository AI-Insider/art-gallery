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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 *
 * @author Simeon
 */

	
@Controller
public class Requests  {
   
    MongoClientURI uri = new MongoClientURI(
    "");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("art-gallery");
    MongoCollection<Document> collection = database.getCollection("Art");
 
    
   @PostMapping("/")
   public String addArt (@RequestParam("uploadFile") MultipartFile uploadedFile, @RequestParam("name") String name) throws IOException{
        

           
         Art art = new Art(name, uploadedFile.getOriginalFilename(),ObjectId.get());
         File file = new File(System.getProperty("user.dir")+"/uploads/"+art.fileName);
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

