/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.service;


/**
 *
 * @author Laud.Ochei
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sanger.data.OutputDao;
import com.sanger.model.InputManifest;
import com.sanger.model.InputStat;
import com.sanger.model.JsonOutput;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("outService")
public class OutputServiceImpl implements OutputService {
	OutputDao outputDao;

	@Autowired
	public void setOutputDao(OutputDao outputDao) {
		this.outputDao = outputDao;
	}

	
        
                
        
        
        @Override
	public List<InputManifest> findManifest() {
		return outputDao.findManifest();
	}
        
        
       
        @Override
	public List<InputStat> findStat() {
		return outputDao.findStat();
	}
       
        
        
        
        @Override
	public List<JsonOutput> findJson() {
            return outputDao.findJson();
	}
        
            
    
}

