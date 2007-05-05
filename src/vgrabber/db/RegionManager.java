/*
 * RegionManager.java
 *
 * Created on December 15, 2006, 12:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;
import java.util.*;
import java.sql.*;
import vgrabber.common.Region;
/**
 *
 * @author vdoni
 */
public class RegionManager {
    
    /** Creates a new instance of RegionManager */
    public RegionManager() {
    }
    public static ArrayList<Region> getRegions(){
    ArrayList<Region> regions=new ArrayList<Region>();
    String sql="select id, name from regiune";
    regions.add(new Region(0,"All",new String[]{"0"}));
    try {
    java.sql.PreparedStatement stRegions=vgrabber.db.Connection.getConnection().prepareStatement(sql);    
    stRegions.execute();
    java.sql.ResultSet rsRegions=stRegions.getResultSet();
    
    while (rsRegions.next()){
        
      regions.add(new Region(rsRegions.getInt("id"),rsRegions.getString("name"),new String[]{"1","2"}));
    }
    return regions;
    } catch (java.sql.SQLException ex){
        
    } finally {
        return regions;
    }
    
    
    }
    
}
