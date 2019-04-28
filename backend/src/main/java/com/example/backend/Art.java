/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.backend;

import java.io.File;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
/**
 *
 * @author Simeon
 */
public class Art {
        public String name;
	public String fileName;

        @Id
        public ObjectId _id;

	Art(String name,String uploadFileName, ObjectId _id){
                this._id = _id;
		this.name = name;
                String extension = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		this.fileName = name+"-"+System.currentTimeMillis()+extension;
                this.fileName = this.fileName.toLowerCase();
                this.fileName = this.fileName.replace(" ", "-");
               
     
	}
          public String get_id() { return _id.toHexString(); }
        
}
