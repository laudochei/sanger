/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sanger.model.InputManifest;
import com.sanger.model.InputStat;
import com.sanger.model.JsonOutput;
import com.sanger.service.OutputService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Laud.Ochei
 */

@RestController
@RequestMapping(value = "/outputapi")
public class OutputController {
    
    
    private OutputService outputService;
	@Autowired
	public void setOutputService(OutputService outputService) {
		this.outputService = outputService;
	}

        
        
        
	
        // list page
        @RequestMapping(value = "/outputlist", method=GET)
        public List<JsonOutput> showJsonOutput(Model model) {
            return outputService.findJson();
        }
        
        
        
        @RequestMapping(value = "/manifestlist", method=GET)
        public List<InputManifest> showManifest(Model model) {
            return outputService.findManifest();
        }
        
        /*
        @RequestMapping(value = "/statlist", method=GET)
        public List<InputStat> showStat(Model model) {
            return outputService.findStat();
        }
        
       */
        
        
    
}