/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.data;

import com.sanger.model.Message;

/**
 *
 * @author Laud.Ochei
 */
public interface MessageDao {
    Message GetMessage(String msg);
    
}
