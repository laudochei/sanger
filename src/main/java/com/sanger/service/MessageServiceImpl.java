/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.service;

import com.sanger.data.MessageDao;
import com.sanger.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

        MessageDao messageDao;
	@Autowired
	public void setMessaageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

        @Override
        public Message GetMessage(String msg) {
            return messageDao.GetMessage(msg);
        }
        
        
}