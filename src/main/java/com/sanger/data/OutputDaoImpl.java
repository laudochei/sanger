/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanger.data;

import com.sanger.model.InputManifest;
import com.sanger.model.InputStat;
import com.sanger.model.JsonOutput;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.sql.DataSource;

//import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;



/**
 *
 * @author Laud.Ochei
 */
@Repository
public class OutputDaoImpl implements OutputDao {
   
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
        

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;      
	}
        
        
        DataSource dataSource;
        @Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    
        
        
        
        
        @Override
	public List<InputManifest> findManifest() {
		String sql = "SELECT * FROM manifest";
		List<InputManifest> result = namedParameterJdbcTemplate.query(sql, new OutputDaoImpl.InputManifestMapper());
		return result;
	}
        
        
        
        @Override
	public List<InputStat> findStat() {
            FileInputStream fin = null;
            String FILENAME = "C:/LAUDOCHEI/SangerTask/hgiprescreen/data/stat.dat.gz"; 
            List<InputStat> statData = new ArrayList<InputStat>();
        try {
            // There are 4 constructor calls here written in full
            fin = new FileInputStream(FILENAME);
            GZIPInputStream gzis = new GZIPInputStream(fin);
            InputStreamReader xover = new InputStreamReader(gzis);
            BufferedReader is = new BufferedReader(xover);
            String line;
            String[] lineparts = null;
            
            // Now read lines of text: the BufferedReader puts them in lines,
            // the InputStreamReader does Unicode conversion, and the
            // GZipInputStream "gunzip"s the data from the FileInputStream.
            
            int count = 0;
            while ((line = is.readLine()) != null){
                lineparts = line.split("\\t+");
                InputStat stats = new InputStat();
                stats.setFilepath(lineparts[0]);
                stats.setSize(Long.parseLong(lineparts[1]));
                stats.setOwner(lineparts[2]);
                stats.setGroupid(lineparts[3]);
                stats.setLastaccessedtime(Integer.parseInt(lineparts[4]));
                stats.setLastmodifiedtime(Integer.parseInt(lineparts[5]));
                stats.setLastchangedtime(Integer.parseInt(lineparts[6]));
                stats.setFiletype(lineparts[7]);
                stats.setInodeid(lineparts[8]);
                stats.setNohardlinks(Integer.parseInt(lineparts[9]));
                stats.setDeviceid(lineparts[10]);
                statData.add(stats);
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
         return statData;
        }

        
	
        @Override
	public List<JsonOutput> findJson() {

                List<JsonOutput> outputted = new ArrayList<JsonOutput>();
                /*
		Connection con = null;
                PreparedStatement stmt = null;
		ResultSet rs = null;
                PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
                
                try {
			con = dataSource.getConnection();
                        String sql = "SELECT * FROM manifest";
                        //String sql1 = "SELECT * FROM products";
			stmt = con.prepareStatement(sql);
                        //stmt1 = con.prepareStatement(sql1);
			rs = stmt.executeQuery();
                        //rs1 = stmt1.executeQuery();
                        int prodCount= 0;
                        
			while(rs.next()) {
                        */    
                            
                            // inner group
                            String user_manifest = "rnd"; //rs.getString("user"); // "rnd";
                            String group_manifest = "hg2"; //rs.getString("groupid"); //"hg2";
                            System.out.println("USER: " + user_manifest +":" + "GROUP: " +group_manifest);
                            
                            String group_groupid = "";
                            String user_groupid = "";
                            int groupid =  0;
                            int stat_groupid = 0;


                            BufferedReader br = null;
                            try {
                                br = new BufferedReader(new FileReader("C:/LAUDOCHEI/SangerTask/hgiprescreen/etc/group.txt"));
                                String line;
                                String[] lineparts = null;
                                int count = 0;
                                while ((line = br.readLine()) != null) {
                                    count++;
                                    System.out.println(line);
                                    lineparts = line.split("[:,]");
                                    for (int i=0; i < lineparts.length; i++)
                                    {
                                      System.out.println(i + " : " + lineparts[i]);

                                      //get user 
                                      if (user_manifest.equals(lineparts[i])){
                                        user_groupid = lineparts[i];
                                        System.out.println("User name: " + user_groupid);
                                      }

                                      //get group 
                                      if (group_manifest.equals(lineparts[i])){
                                        group_groupid = lineparts[i];
                                        System.out.println("Group name: " + group_groupid);
                                      }


                                      if (user_manifest.equals(user_groupid) || group_manifest.equals(group_groupid)){
                                          // get groupNo
                                          if (i==2){
                                            groupid = Integer.parseInt(lineparts[i]);
                                            System.out.println("GroupID: " + groupid);
                                            
                                            
                                            // stat file
                                            FileInputStream fin = null;
                                            String FILENAME = "C:/LAUDOCHEI/SangerTask/hgiprescreen/data/stat.dat.gz"; 
                                            List<InputStat> statData = new ArrayList<InputStat>();
                                            try {
                                            // There are 4 constructor calls here written in full
                                            fin = new FileInputStream(FILENAME);
                                            GZIPInputStream gzis = new GZIPInputStream(fin);
                                            InputStreamReader xover = new InputStreamReader(gzis);
                                            BufferedReader is = new BufferedReader(xover);
                                            String lined;
                                            String[] lineparted = null;

                                            // Now read lines of text: the BufferedReader puts them in lines,
                                            // the InputStreamReader does Unicode conversion, and the
                                            // GZipInputStream "gunzip"s the data from the FileInputStream.

                                            int counted = 0;
                                            Long stat_inode = null;
                                            Long stat_size = null;
                                            String stat_latest = "";
                                            
                                            while ((lined = is.readLine()) != null){
                                                lineparted = lined.split("\\t+");
                                                
                                                for (int j=0; j < lineparts.length; j++)
                                                {
                                                   System.out.println(j + " : " + lineparted[j]);
                                                    
                                                    if (groupid == Integer.parseInt(lineparts[i])){
                                                        stat_groupid = Integer.parseInt(lineparts[i]);
                                                        System.out.println("Found a match in Stat.dat.gz: " + stat_groupid);
                                                        
                                                    }
                                                    
                                                    
                                                    if (stat_groupid == groupid){
                                                        //get file path
                                                        if (j == 0){
                                                            stat_latest = lineparted[j];
                                                            System.out.println("Latest file: " + stat_latest);
                                                        }
                                                        else {
                                                            stat_latest = null;
                                                        }
                                                        // get size
                                                        if (j == 1){
                                                            stat_size = Long.parseLong(lineparted[j]);
                                                            System.out.println("File size: " + stat_size );
                                                        }
                                                        else{
                                                            stat_size = Long.valueOf(0);
                                                        }
                                                        //get inode
                                                        if (j == 8){
                                                            stat_inode = Long.parseLong(lineparted[j]);
                                                            System.out.println("Inodes: " + stat_inode);
                                                        }
                                                        else{
                                                            stat_inode = Long.valueOf(0);
                                                        }
                                                        
                                                        //populat the Json output object
                                                        JsonOutput jsonOutput = new JsonOutput();
                                                        jsonOutput.setUser(user_manifest);
                                                        jsonOutput.setGroup(group_manifest);
                                                        jsonOutput.setInodes(stat_inode);
                                                        jsonOutput.setSize(stat_size);
                                                        jsonOutput.setLatest(stat_latest);
                                                        outputted.add(jsonOutput);  
                                                        
                                                    }
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                }
                                                
                                                
                                                
                                            }

                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                                        } finally {
                                            try {
                                                fin.close();
                                            } catch (IOException ex) {
                                                Logger.getLogger(OutputDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
        
                                            // end of stat file
                                           }  
                                      }




                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (br != null) {
                                        br.close();
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
        
                            //inner group
 
//                        }
//                        
//                } 
//		catch (SQLException e) {
//                    e.printStackTrace();
//		}               
            
            
            
            return outputted;
        }
        
        
        
        
        
                
        
        
        
        private SqlParameterSource getSqlParameterByModel(InputManifest inputManifest) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("manifestno", inputManifest.getManifestno());
                paramSource.addValue("user", inputManifest.getUser());
		paramSource.addValue("groupid", inputManifest.getGroupid());
		return paramSource;
	}

        
        
	private static final class InputManifestMapper implements RowMapper<InputManifest> {

		public InputManifest mapRow(ResultSet rs, int rowNum) throws SQLException {
			InputManifest manifest = new InputManifest();
                        manifest.setManifestno((rs.getInt("manifestno")));
                        manifest.setUser((rs.getString("user")));
                        manifest.setGroupid((rs.getString("groupid")));
                        
                        return manifest;
		}
	}   
        
}




