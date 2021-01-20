import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class A1063330_checkpoint6_QueryDB {
    //Description : the driver description of mysql
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Description : the protocol description of mysql
    private static final String PROTOCOL = "jdbc:mysql:";
    //Description : the obstacle set queried from database.
    private static ArrayList<Integer[]> data;
    //Description : the filename of obstacle image queried from database.
    private static HashMap<Integer, String> typeChar;
    //Description : the ID of the map in database;
    private static String mapID;

    public A1063330_checkpoint6_QueryDB(String mapID){
        this.data  = new ArrayList<Integer[] >();
        this.typeChar = new HashMap<Integer, String>();
        this.mapID = mapID;
        queryData(this.data, this.typeChar);
    }

    private static void queryData(ArrayList<Integer[]> data,HashMap<Integer, String> typeChar) {
    /*********************************The Past TODO (Checkpoint2)********************************
     * 
     * TODO(Past) Querying the map size and the barrier location from the server, and set it into WIDTH, HEIGHT, Arraylist data.
     * 
     * TODO(Past) Querying the bar_type and the corresponding file_name from the server, and set it into HashMap.
     * 
     * Hint:  for ckp2 to after, you need replace querying  column "file_name" with querying  column "display". 
     * 
     **********************************The End of the TODO***************************************/

     /********************************************************************************************
     START OF YOUR CODE
     ********************************************************************************************/
    	try {
			Class.forName(DRIVER);
		} catch (Exception err) {
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String url = "//140.127.220.220 :3306";
		String dbName = "/CHECKPOINT";
		String username = "checkpoint";
		String password = "ckppwd";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(PROTOCOL + url + dbName, username, password);
			Statement s = conn.createStatement();
			ResultSet rs = null;
			ResultSet rs2 = null;
			ResultSet rs3 = null;
			rs = s.executeQuery("SELECT height,width FROM map WHERE map.map_id =" + mapID);
			if (rs.next() == false) {
				rs.close();
				System.exit(0);
			} else {
				typeChar.clear();
				data.clear();
//				rs.previous();
//				while (rs.next()) {
//					try {
//					HEIGHT = rs.getInt("height");
//					WIDTH = rs.getInt("width");
//					}
//					catch (SQLException err){
//						err.printStackTrace(System.err);
//					}
//				}
				rs.close();
				rs2 = s.executeQuery("SELECT row_idx,column_idx,bar_type FROM barrier WHERE barrier.map_id =" + mapID);
				while (rs2.next()) {
					try {
						int x = rs2.getInt("row_idx");
						int y = rs2.getInt("column_idx");
						int id = rs2.getInt("bar_type");
						Integer[] bar = new Integer[] { x, y, id };
						data.add(bar);
//						i = i + 3;
					} catch (SQLException err) {
						err.printStackTrace(System.err);
					}
				}
				rs2.close();
				rs3 = s.executeQuery(
						"SELECT barrier_type.bar_type,barrier_type.file_name FROM barrier_type,barrier WHERE barrier_type.bar_type = barrier.bar_type");
				while (rs3.next()) {
					try {
						String filename = rs3.getString("file_name");
						int bar_id = rs3.getInt("bar_type");
						typeChar.put(bar_id, filename);
					} catch (SQLException err) {
						err.printStackTrace(System.err);
					}
				}
				rs3.close();
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace(System.err);
			System.exit(0);
		}
     
    /********************************************************************************************
     END OF YOUR CODE
     ********************************************************************************************/
    }
    public ArrayList getObstacle(){
        return this.data;
    }
    public void setObstacle(ArrayList<Integer[]> data){
        this.data = data;
    }
    public String getMapID(){
        return this.mapID;
    }
    public void setMapID(String mapID){
        this.mapID = mapID;
    }
    public HashMap getObstacleImg(){
        return this.typeChar;
    }
    public void setObstacleImg(HashMap<Integer, String> typeChar){
        this.typeChar = typeChar;
    }
}
