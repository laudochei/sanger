/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.service;

import com.sanger.model.InputManifest;
import com.sanger.model.InputStat;
import com.sanger.model.JsonOutput;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface OutputService {
   
    List<InputManifest> findManifest();
    List<InputStat> findStat();
    List<JsonOutput> findJson();
    
}