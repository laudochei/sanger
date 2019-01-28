package com.sanger.sanger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.sanger.controller", "com.sanger.data", "com.sanger.exception", "com.sanger.model", "com.sanger.service"})
//@ComponentScan(basePackages = {"com.sanger.data", "com.sanger.controller", "com.sanger.exception", "com.sanger.model", "com.sanger.service"})
//@EnableJpaRepositories(basePackages = {"com.sanger.data", "com.sanger.controller", "com.sanger.exception", "com.sanger.model", "com.sanger.service"})
//@EntityScan(basePackages = {"com.sanger.model"})

public class SangerApplication extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SangerApplication.class);
    }


	public static void main(String[] args) throws IOException {
		SpringApplication.run(SangerApplication.class, args);
                //readGZ();
                //readTX();
	}

        
        
        public static void readGZ() throws FileNotFoundException, IOException{ 
            
            
            String FILENAME = "C:/LAUDOCHEI/SangerTask/hgiprescreen/data/stat.dat.gz"; //file.txt.gz";
            Map<Integer, String> stat = new HashMap<Integer, String>();
            
            // Since there are 4 constructor calls here, I wrote them out in full.
            // In real life you would probably nest these constructor calls.
            FileInputStream fin = new FileInputStream(FILENAME);
            GZIPInputStream gzis = new GZIPInputStream(fin);
            InputStreamReader xover = new InputStreamReader(gzis);
            BufferedReader is = new BufferedReader(xover);

            String line;
            String[] lineparts = null;
            // Now read lines of text: the BufferedReader puts them in lines,
            // the InputStreamReader does Unicode conversion, and the
            // GZipInputStream "gunzip"s the data from the FileInputStream.
            int count = 0;
            ArrayList<String> statlist=new ArrayList<String>();
            while ((line = is.readLine()) != null){
                
                //System.out.println("Read: " + count++);
                System.out.println("Read: " + line);
                //count++;
                stat.put(count++,line); 
                statlist.add(line);
             
               // System.out.println("Read: " + line.toString());
                lineparts = line.split("\\t+");
                
                
                
                    // This line of code throws NullPointerException 
                    // because ptr is null 
                //lineparts = line.split("\\t+");
                
            
               // System.out.println("File path: " + lineparts[0]);
                /*
                System.out.println("Size: " + lineparts[1]);
                System.out.println("Owner: " + lineparts[2]);
                System.out.println("Group: " + lineparts[3]);
                System.out.println("last access time: " + lineparts[4]);
                System.out.println("last modified time: " + lineparts[5]);
                System.out.println("last change time: " + lineparts[6]);
                System.out.println("file type: " + lineparts[7]);
                System.out.println("Inode: " + lineparts[8]);
                System.out.println("Number of hardlinks: " + lineparts[9]);
                System.out.println("Device ID: " + lineparts[10]);
                */
            }  
                System.out.println("Record size: " + statlist.size());
                
                /*
                for (int i = 0; i < statlist.size(); i++) {
                    System.out.println("Record details: " + statlist.get(i));
                    System.out.println("Record details: " + statlist.get(i).toString());
                }
                
                for (int i = 0; i < stat.size(); i++) {
                    System.out.println("Record details: " + stat.get(i));
                    System.out.println("Record details: " + stat.get(i).toString());
                }
                
                for (Integer key : stat.keySet()) {
                    System.out.println("------------------------------------------------");
                    System.out.println("---Quality Assurance Report---2");
                    System.out.println("key: " + key + " value: " + stat.get(key));
                }
                */
                

            }
        
        
            
        
        public static void readTX() throws FileNotFoundException, IOException{ 
            String user_manifest =  "rnd";
            String group_manifest = "hg2";
            String group_groupid = "";
            String user_groupid = "";
            int groupid =  0;
            
            
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
        
        
        }
       
        
        
}

