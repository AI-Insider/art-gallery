/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend;

import java.io.IOException;
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
   @PostMapping("/")
   public String addArt (@RequestParam("uploadFile") MultipartFile uploadedFile, @RequestParam("name") String name) throws IOException{
        

           
         Art art = new Art(name, uploadedFile.getOriginalFilename());
         uploadedFile.transferTo(art.file);
         
         return "redirect:index.html";
      
         
        
        
   }
   @GetMapping("/")
   public String sendPage(){
       return "index.html";
   }
   
  
   
   
   
   
}
