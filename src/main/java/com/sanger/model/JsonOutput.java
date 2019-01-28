/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.model;

/**
 *
 * @author Laud.Ochei
 */
public class JsonOutput {
    private String user;
    private String group;
    private Long inodes;
    private Long size;
    private String latest;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getInodes() {
        return inodes;
    }

    public void setInodes(Long inodes) {
        this.inodes = inodes;
    }

    

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
    
    
}
