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
public class InputStat {
    
private String filepath;
private Long size;
private String Owner;
private String groupid;
private long lastaccessedtime;
private long lastmodifiedtime;
private long lastchangedtime;
private String filetype;
private String inodeid;
private Integer nohardlinks;
private String deviceid;    

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public long getLastaccessedtime() {
        return lastaccessedtime;
    }

    public void setLastaccessedtime(long lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }

    public long getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(long lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public long getLastchangedtime() {
        return lastchangedtime;
    }

    public void setLastchangedtime(long lastchangedtime) {
        this.lastchangedtime = lastchangedtime;
    }

    
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getInodeid() {
        return inodeid;
    }

    public void setInodeid(String inodeid) {
        this.inodeid = inodeid;
    }

    public Integer getNohardlinks() {
        return nohardlinks;
    }

    public void setNohardlinks(Integer nohardlinks) {
        this.nohardlinks = nohardlinks;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    



}
