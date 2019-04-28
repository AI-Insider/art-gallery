/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend;

import java.io.File;

/**
 *
 * @author Simeon
 */
public class Art {
        public String name;
	public String fileName;
        public File file;

	Art(String name,String uploadFileName){
                
		this.name = name;
                String extension = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		this.fileName = System.getProperty("user.dir")+"/public/uploads/"+name+"-"+System.currentTimeMillis()+extension;
                this.fileName = this.fileName.toLowerCase();
                this.fileName = this.fileName.replace(" ", "-");
                file = new File(this.fileName);
     
	}
}
