import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class is used to connect to the database and has several functions that let's you create and manage it
 * @author Refined Storage developers
 *
 */
public class DbFunctions {
	
	private static String db_path_nuno = "jdbc:sqlite:C:\\RefinedStorage\\DB\\database.db";
	
	/**
	 * This method is used to create the database. It creates the tables user, material, 
	 * orders and quantity if they do not already exist. It also inputs values into the table user,
	 * orders, and quantity
	 * @throws Throwable SQLException
	 */
	public static void com() throws Throwable {
        Connection connection = null;
		try
		        {     	
		          connection = DriverManager.getConnection(db_path_nuno);
		          Statement statement = connection.createStatement();
		          statement.setQueryTimeout(30);

		          DatabaseMetaData dbm = connection.getMetaData();
		          ResultSet tables = dbm.getTables(null, null, "user", null);
		          if (tables.next()) {
		          }
		          else {
		        	  statement.executeUpdate("CREATE TABLE user (\r\n"
				          		+ "    id integer PRIMARY KEY,\r\n"
				          		+ "    name text NOT NULL,\r\n"
				          		+ "    priority integer NOT NULL,  -- 1 = admin; 2 = normal user\r\n"
				          		+ "    password text NOT NULL\r\n"
				          		+ ")");
		        	  statement.executeUpdate("INSERT INTO user VALUES (1, 'Admin', 1, '0000')");
		          }
		          
		          tables = dbm.getTables(null, null, "material", null);
		          if (tables.next()) {
		          }
		          else {
		        	    statement.executeUpdate("CREATE TABLE material (\r\n"
				          		+ "    id integer PRIMARY KEY AUTOINCREMENT,\r\n"
				          		+ "    name text NOT NULL,\r\n"
				          		+ "    brand text NOT NULL,\r\n"
				          		+ "    quantity integer NOT NULL,\r\n"
				          		+ "    alteration_date text NOT NULL\r\n"
				          		+ ")");
		          }
		          
		          tables = dbm.getTables(null, null, "orders", null);
		          if (tables.next()) {
		          }
		          else {
		        	  statement.executeUpdate("CREATE TABLE orders (\r\n"
				          		+ "    id integer NOT NULL,\r\n"
				          		+ "    type varchar NOT NULL,\r\n"
				          		+ "    urgency integer NOT NULL\r\n"
				          		+ ")");
		              
			          statement.executeUpdate("INSERT INTO orders VALUES (1, 'PO', 3)");
			          statement.executeUpdate("INSERT INTO orders VALUES (2, 'TO', 2)");
		          }
		          
		          tables = dbm.getTables(null, null, "quantity", null);
		          if (tables.next()) {
		          }
		          else {
		        	  statement.executeUpdate("CREATE TABLE quantity (\r\n"
				          		+ "    orders integer NOT NULL REFERENCES orders,\r\n"
				          		+ "    material integer NOT NULL REFERENCES material DEFERRABLE INITIALLY DEFERRED,\r\n"
				          		+ "    quantity integer,\r\n"
				          		+ "    CHECK (quantity > 0)\r\n"
				          		+ ")");
		        	  
			          statement.executeUpdate("INSERT INTO quantity VALUES (1, 1, 2)");
			          statement.executeUpdate("INSERT INTO quantity VALUES (1, 2, 5)");
			          statement.executeUpdate("INSERT INTO quantity VALUES (2, 1, 1)");
			          statement.executeUpdate("INSERT INTO quantity VALUES (2, 2, 3)");
		          }

		          connection.close();
		        }
		        catch(SQLException e)
		        {
		          System.err.println(e.getMessage());
		        }
		        finally
		        {
		          try
		          {
		            if(connection != null)
		              connection.close();
		          }
		          catch(SQLException e)
		          {
		            System.err.println(e.getMessage());
		          }
		   
		        }
		
		      }
	
	/**
	 * This method is used to connect to the database
	 * @return the connection
	 */
    private Connection connect() {
    	
        String sqlUrl = db_path_nuno;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(sqlUrl);
            
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

	/**
	 * This method is used to get the name that is correspondent to the passed param id
	 * @param id - the desired id
	 * @return the name of the correspondent id
	 */
	public String get_material_from_ID(int id) {
		String sql = "SELECT material.name FROM material WHERE id =" + id;
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			String result = rs.getString("name");
			if(conn != null)
	              conn.close();
			return result;
		}
	    catch (SQLException e) {
	           System.out.println(e.getMessage());
	    }
		return null;
	}
	/**
	 * This method is used to get the priority(admin, employee) of the corresponding id
	 * @param id - the specified id
	 * @return 1 in case of admin, 2 in case of employee, 0 in case of error
	 */
	public int get_prio_from_ID(int id) {
		String sql = "SELECT user.priority FROM user WHERE id =" + id;
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			int result = rs.getInt("priority");
			if(conn != null)
	              conn.close();
			return result;
		}
	    catch (SQLException e) {
	           System.out.println(e.getMessage());
	    }
		return 0;
	}
	/**
	 * This method is used to get the name and password of the corresponding id
	 * @param id - the desired id
	 * @return string with the name and password of the desired id
	 */
	public String[] getName_and_pw_FromID(int id) {
		String sql = "SELECT user.name, user.password FROM user WHERE id =" + id;
		try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			
			String[] user = new String[2];
			
			user[0] = rs.getString("name");
			user[1] = rs.getString("password");
			if(conn != null)
	              conn.close();
			
			System.out.println(user[0] + user[1]);
			
			return user;
		}
	    catch (SQLException e) {
	           System.out.println(e.getMessage());
	    }
		return null;
	}
	
	/**
	 * This method is used to get the count of materials present in the database
	 * @return the number of material that are present
	 * @throws Throwable SQLException
	 */
	public int getMaterialsNumber() throws Throwable  { //GUI
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM material");
		int count=-1;
		if(rs.next()) {
			   count = Integer.parseInt(rs.getString(1));
			}
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        return count;
		
	}
	/**
	 * This method is used to list all the materials present in the database
	 * @return string with all the values of the table materials, and null if error
	 * @throws Throwable SQLException
	 */
	public String[][] listMaterials() throws Throwable  { //GUI
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM material");
		 
		System.out.println("id | name | brand | quantity | alteration_date" );
		int i = 1, j = this.getMaterialsNumber();
		if(j==-1) {
			System.out.println("Error getting material count.");
			return null;
		}
		System.out.println(j);
		String[][] output = new String[j+1][5];
		output[0][0]= String.valueOf(j);
		while(rs.next()) {
			output[i][0] = String.valueOf(rs.getInt("id"));
			output[i][1] = rs.getString("name");
			output[i][2] = rs.getString("brand");
			output[i][3] = String.valueOf(rs.getInt("quantity"));
			output[i][4] = rs.getString("alteration_date");
			i++;
		}
		System.out.println(i);
	
		try
        {
          if(connection != null)
        	  connection.close();
         }
        catch(SQLException e)
        {
        	System.err.println(e.getMessage());
        }
        
		return output;
	}
	/**
	 This method is used to get the count of orders present in the database
	 * @return the number of orders that are present
	 * @throws Throwable SQLException
	 */
	public int getOrdersNumber() throws Throwable  { //GUI
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM orders");
		int count=-1;
		if(rs.next()) {
			   count = Integer.parseInt(rs.getString(1));
			}
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        return count;
		
	}
	/**
	 * This method is used to list all the values of the order present in the table orders
	 * @return string with all the values of the table orders
	 * @throws Throwable SQLException
	 */
	public String[][] listOrders() throws Throwable  { //GUI
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM orders");
		 
		System.out.println("id | type | urgency" );
		int i = 1, j = this.getOrdersNumber();
		if(j==-1) {
			System.out.println("Error getting material count.");
			return null;
		}
		System.out.println(j);
		String[][] output = new String[j+1][5];
		output[0][0]= String.valueOf(j);
		while(rs.next()) {
			output[i][0] = String.valueOf(rs.getInt("id"));
			output[i][1] = rs.getString("type");
			output[i][2] = rs.getString("urgency");
			i++;
		}
		System.out.println(i);
		try
        {
          if(connection != null)
        	  connection.close();
         }
        catch(SQLException e)
        {
        	System.err.println(e.getMessage());
        }
        
		return output;
	}
	/**
	 * This method is used to get the count where orders corresponds to the parameter id
	 * @param id - the desired id
	 * @return the count where orders correspond to the associated id
	 * @throws Throwable SQLException
	 */
	public int getMaterialNumberInOrder(int id) throws Throwable  { //GUI
			
			Connection connection = null;
	        connection = DriverManager.getConnection(db_path_nuno);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM quantity WHERE orders="+id);
			int count=-1;
			if(rs.next()) {
				   count = Integer.parseInt(rs.getString(1));
				}
			try
	        {
	          if(connection != null)
	            connection.close();
	        }
	        catch(SQLException e)
	        {
	          System.err.println(e.getMessage());
	        } 
	        return count;
			
		}
	/**
	 * This method is used to list the material by the specified parameter id
	 * @param id - the desired id
	 * @return an integer with the material and quantity values, and null if error
	 * @throws Throwable SQLException
	 */
	public int[][] listMaterialByOrderID(int id) throws Throwable  { //GUI
			
			Connection connection = null;
	        connection = DriverManager.getConnection(db_path_nuno);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM quantity WHERE orders=" + id);
			int i = 1, j = this.getMaterialNumberInOrder(id);
			if(j==-1) {
				System.out.println("Error getting material count.");
				return null;
			}
			System.out.println(j);
			int[][] output = new int[j+1][2];
			output[0][0]= j;
			while(rs.next()) {
				output[i][0] = rs.getInt("material");
				output[i][1] = rs.getInt("quantity");
				i++;
			}
			try
	        {
	          if(connection != null)
	        	  connection.close();
	         }
	        catch(SQLException e)
	        {
	        	System.err.println(e.getMessage());
	        }
			return output;
		}
	
	/**
	 * This method is used to get the corresponding quantity from the desired Id 
	 * @param id - the wanted id
	 * @return the quantity of the corresponding id
	 * @throws Throwable SQLException
	 */
	public int getQuantityFromID(int id) throws Throwable  { //GUI
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM material where id="+id);
		int result = rs.getInt("quantity");
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        return result;
		
	}
	/**
	 * This method is used to change the quantity of the passed id with the qty parameter
	 * @param id - the wanted id that is desired to update the quantity from
	 * @param qty - the new desired quantity  
	 * @throws Throwable SQLException
	 */
	public void updateQuantityFromID(int id, int qty) throws Throwable  { //GUI
		DbFunctions.com();
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
        System.out.println("TEST");
		Statement statement = connection.createStatement();
		System.out.println("UPDATE material SET quantity=" + qty + " WHERE id="+id);
		statement.executeUpdate("UPDATE material SET quantity=" + qty + " WHERE id="+id);
		System.out.println("TEST");
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
        
		
	}
	/**
	 * This method is used to get the orders that are present in the database
	 */
	public void getOrders() {
		String sql = "SELECT orders.id, orders.type, orders.urgency FROM orders";
		
	    try (Connection conn = this.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
		    while (rs.next()) {
		    	System.out.println(rs.getInt("id") +  "\t" + 
		    	rs.getString("user") + "\t" +
		    	rs.getInt("priority"));
		    	if(conn != null)
		            conn.close();
		        }
	    }
	    catch (SQLException e) {
	           System.out.println(e.getMessage());
	    }
	   
	}
	
	/**
	 * This method is used to list some of the values that exist in the table users (id, name, priority) 
	 * @return the result set that contains the users
	 * @throws Throwable SQLException - if a database access error occurs or the url is null
	 * , SQLTimeoutException - when the driver has determined that the timeout value specified 
	 * by the setLoginTimeout method has been exceeded and has at least tried to cancel the current 
	 * database connection attempt
	 */
	public ResultSet listUsers() throws Throwable { //GUI
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery("SELECT user.id, user.name, user.priority FROM user");
		 
		 System.out.println("id | name | priority");
		 
		 return rs;
	}
	
	/**
	 * This method is used to check if the id and password that are passed are present in the database
	 * @param id - pretended id
	 * @param pass - password of the corresponding id
	 * @return the temp_user_from_db
	 * @throws Throwable SQLException
	 */
	public temp_user_from_db login(int id, String pass) throws Throwable {
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery("SELECT user.id, user.password, user.name, user.priority FROM user");
         while(rs.next())
         {
          
          if((id == rs.getInt("id")) && (pass.equals(rs.getString("password")))) {
        	  
        	  temp_user_from_db u = new temp_user_from_db(rs.getString("name"), rs.getString("password"), rs.getInt("id"), rs.getInt("priority"));
        	  
        	  try
	          {
	            if(connection != null)
	              connection.close();
	          }
	          catch(SQLException e)
	          {
	            // connection close failed.
	            System.err.println(e.getMessage());
	          }
        	  
        	  return u;
        	  
          }    
         }
         return null;
	}
	
	
	
	/**
	 * This method is used to list all the values that exist in the table material
	 * @throws Throwable SQLException
	 */
	public void see_inventory() throws Throwable {
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		 ResultSet rs = statement.executeQuery("SELECT material.id, material.name, material.brand, material.quantity, material.alteration_date\r\n"
		 		+ "FROM material");
		 
		 System.out.println("id | name | brand | quantity | alteration_date" );
		 		 
         while(rs.next())
         {
           System.out.print(rs.getInt("id") + " | ");      
           System.out.print(rs.getString("name")  + " | ");
           System.out.print(rs.getString("brand")  + " | ");
           System.out.print(rs.getInt("quantity")  + " | ");
           System.out.println(rs.getString("alteration_date"));
           
         }
         
		 try
         {
           if(connection != null)
             connection.close();
         }
         catch(SQLException e)
         {
           System.err.println(e.getMessage());
         }
         
		
	}
	
	
	/**
	 * This method is used to execute queries
	 * @param query - the wanted query
	 * @throws Throwable SQLException
	 */
	public void execute_simple_query(String query) throws Throwable {
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		
		statement.executeUpdate(query);
		
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
			
	}
	
	
	
	/**
	 * This method is used to see if the wanted id already exists in the determined table 
	 * @param id - the pretended id 
	 * @param table -  the pretended table (user, material, orders)
	 * @return true if the id exists in the table, false if it doesn't
	 * @throws Throwable SQLException
	 */

	public static boolean check_id(int id, String table) throws Throwable {
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
        int id2;
        boolean exists = false;
		
		try
        {
		PreparedStatement preparedStatement=connection.prepareStatement("select * from " + table);
		   ResultSet resultSet=preparedStatement.executeQuery();
	        while(resultSet.next()){
	              id2 =resultSet.getInt("id");
	      
	              if(id2==id) {
	            	  exists = true;
	              }
	        }
	       
          if(connection != null)
            connection.close();
          return exists;
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
		
		return exists;
	}
	
	
	
	/**
	 *  This method is used to get a string with the values of the table 
	 * material that are associated to the corresponding name 
	 * @param id - the pretended id of the search
	 * @return the values of the table material in a string
	 * @throws Throwable SQLException
	 */
	public String[][] searchByID(String id) throws Throwable  { 
		
		Connection connection = null;
	    connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM material WHERE material.id = " + id);
		 
		System.out.println("id | name | brand | quantity | alteration_date" );
		int i = 1;
		int j = 1;
		System.out.println(j);
		String[][] output = new String[j+1][5];
		output[0][0]= String.valueOf(j);
		while(rs.next()) {
			output[i][0] = String.valueOf(rs.getInt("id"));
			output[i][1] = rs.getString("name");
			output[i][2] = rs.getString("brand");
			output[i][3] = String.valueOf(rs.getInt("quantity"));
			output[i][4] = rs.getString("alteration_date");
			i++;
		}
		System.out.println(i);
		
		 try
	     {
	       if(connection != null)
	         connection.close();
	     }
	     catch(SQLException e)
	     {
	       // connection close failed.
	       System.err.println(e.getMessage());
	     }
	     
		return output;
	}
	/**
	 * This method is used to get the count of the quantity of all the materials that have the associated name
	 * @param name - the name that is desired to get the count
	 * @return the count of the materials with the parameter name
	 * @throws Throwable SQLException
	 */
	public int getMaterialsNumberByName(String name) throws Throwable  { 
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM material WHERE material.name LIKE '%" + name + "%'");
		int count=-1;
		if(rs.next()) {
			   count = Integer.parseInt(rs.getString(1));
			}
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        return count;
		
	}
	/**
	 * This method is used to get a string with the values of the table 
	 * material that are associated to the corresponding name 
	 * @param name - the pretended name of the search
	 * @return the values of the table material in a string
	 * @throws Throwable SQLException
	 */
	public String[][] searchByName(String name) throws Throwable  { 
		
		Connection connection = null;
	    connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM material WHERE material.name LIKE '%" + name + "%'");
		 
		System.out.println("id | name | brand | quantity | alteration_date" );
		int i = 1;
		int j = this.getMaterialsNumberByName(name);
		if(j==-1) {
			System.out.println("Error getting material count.");
		}
		System.out.println(j);
		String[][] output = new String[j+1][5];
		output[0][0]= String.valueOf(j);
		while(rs.next()) {
			output[i][0] = String.valueOf(rs.getInt("id"));
			output[i][1] = rs.getString("name");
			output[i][2] = rs.getString("brand");
			output[i][3] = String.valueOf(rs.getInt("quantity"));
			output[i][4] = rs.getString("alteration_date");
			i++;
		}
		System.out.println(i);
		
		 try
	     {
	       if(connection != null)
	         connection.close();
	     }
	     catch(SQLException e)
	     {
	       System.err.println(e.getMessage());
	     }
	     
		return output;
	}

	/**
	 * This method is used to get the count of the quantity of all the materials that have the associated brand
	 * 
	 * @param brand - the brand that is desired to get the count
	 * @return the count of the materials with the parameter brand
	 * @throws Throwable SQLException
	 */
	public int getMaterialsNumberByBrand(String brand) throws Throwable  { 
		
		Connection connection = null;
        connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM material WHERE material.brand LIKE '%" + brand + "%'");
		int count=-1;
		if(rs.next()) {
			   count = Integer.parseInt(rs.getString(1));
			}
		try
        {
          if(connection != null)
            connection.close();
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } 
        return count;
		
	}

	
	/**
	 * This method is used to get a string with the values of the table 
	 * material that are associated to the corresponding brand 
	 * @param brand - the pretended brand of the search
	 * @return the values of the table material in a string
	 * @throws Throwable SQLException
	 */
	public String[][] searchByBrand(String brand) throws Throwable  { 
		
		Connection connection = null;
	    connection = DriverManager.getConnection(db_path_nuno);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM material WHERE material.brand LIKE '%" + brand + "%'");
		 
		System.out.println("id | name | brand | quantity | alteration_date" );
		int i = 1;
		int j = this.getMaterialsNumberByBrand(brand);
		if(j==-1) {
			System.out.println("Error getting material count.");
		}
		System.out.println(j);
		String[][] output = new String[j+1][5];
		output[0][0]= String.valueOf(j);
		while(rs.next()) {
			output[i][0] = String.valueOf(rs.getInt("id"));
			output[i][1] = rs.getString("name");
			output[i][2] = rs.getString("brand");
			output[i][3] = String.valueOf(rs.getInt("quantity"));
			output[i][4] = rs.getString("alteration_date");
			i++;
		}
		System.out.println(i);

		 try
	     {
	       if(connection != null)
	         connection.close();
	     }
	     catch(SQLException e)
	     {
	       System.err.println(e.getMessage());
	     }
	     
		return output;
	}
/**
 * This method checks if the string text can be converted into a valid integer
 * 
 * @param text - the intended text for conversion
 * @return returns the number of the valid integer, else returns 0 in case 
 * of an invalid string to integer conversion
 */
	public int CheckValidId(String text) {
		int i;
		String regex = "[+-]?[0-99999]+"; 
	    Pattern p = Pattern.compile(regex); 
	    Matcher m = p.matcher(text);
	    
	    if(m.find() && m.group().equals(text)) {
	    	i = Integer.parseInt(text);
	    }
	    else {
	    	i=0;
	    }
		return i;
	}
}



