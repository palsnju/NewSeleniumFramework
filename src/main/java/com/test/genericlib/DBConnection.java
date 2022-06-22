package com.test.genericlib;

import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBConnection {
	final Logger log = Logger.getLogger(DBConnection.class);
	public FileUtils fLib = new FileUtils();
	
public  Connection createConnection(String dbName) throws Throwable {
		Properties prop=fLib.getPropertyFileObject();
		String envName=prop.getProperty("envName");
		 String hostName = prop.getProperty(envName+".hostName"); // update me
	     String user =  prop.getProperty(envName+".user");// update me
	     String password = prop.getProperty(envName+".password"); // update me
	     String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
	        + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
	     
	     	log.info("ConnectionStart");
			Connection con = DriverManager.getConnection(url);
            String schema = con.getSchema();
            log.info("Successful connection - Schema: " + schema);
            log.info("=========================================");
             return con;
        }
	
    public  ResultSet  doOperation(Connection con, String sql) throws SQLException {
                   	
        	Statement statement = con.createStatement();
            String type=sql.replace("\"", "").split(" ")[0];
            if(type.toLowerCase().equals("select")) {
            	ResultSet res = statement.executeQuery(sql);
                return res;
        	}
            else if(type.toLowerCase().equals("update")||type.toLowerCase().equals("delete")) {
                	
            	 statement.executeUpdate(sql);
            	 log.info(type+" operation done");
                 return null; 		
            }
            
            else{
            	 log.debug("not same to update, delete and select ");
            }
            		
            log.info("=========================================");
			return null;
            }
        
        public void closeConnection(Connection conn) throws SQLException
        {
        	conn.close();
        	 log.info("ConnectionClose");
        }
}
