import java.sql.ResultSet;


/**
 * This class represents the admin, and has all of the admin only functions
 * @author Refined Storage developers
 *
 */
public class Admin extends Employee{
	/**
	 * This constructor sets the passed parameters as variables
	 * @param id - id in use
	 * @param user_name - name in use
	 * @param pw - password in use
	 * @param prio - role in use
	 */
	public Admin(int id, String user_name, String pw, int prio) {
		
		super(id, user_name, pw, prio);
		
	}
	

	/**
	 * This method is used to add a new user to the table users
	 * @param Db - the corresponding database
	 * @param role - string with the role that is to be assigned to the user: admin, employee
	 * @param i - integer with the id that is to be assigned to the user
	 * @param name - string with the name that is to be assigned to the user
	 * @param password - string with the password that is to be assigned to the user
	 * @return integer: 0 if was correctly added, 1 if the id is currently being used, 
	 * 2 if it is an invalid id, 3- if it is an invalid role, 4 if there is an error
	 */
	public int add_user(DbFunctions Db, String role, int i, String name, String password){
		
		try {
			int p = 0;
			if(role.equals("employee")) {
				p = 2;
			} else if(role.equals("admin")) {
				p = 1;
			} else p = 3;
			
			
			if(DbFunctions.check_id(i, "user")== true) {
				return 1;
			} else if(i!=0 && p != 3){
				
				String query = "INSERT INTO user VALUES (" + i + ", '" + name + "', " + p + ", '" + password + "')";
				
				Db.execute_simple_query(query);
				return 0;
			}
			else if(i==0) {
				return 2;
			}
			else if(p == 3) {
				return 3;
			}else {
				return 4;
			}
			
			
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 4;
		}
		
		
	}
	
	
	/**
	 * This method is used to remove a user that is currently in the database
	 * @param Db - the database
	 * @param i2 - integer with the user id that is to be removed
	 * @param Rs - the refined storage
	 * @return integer: 0 if it was correctly removed, 1 if it the id isn't present, 2 if it is an invalid user, 3 if error
	 */
	public int rem_user(DbFunctions Db, int i2, Refined_storage Rs){
		
		String delete_query = "DELETE FROM user WHERE id = " + i2 + ";";
		
		try {
			if((DbFunctions.check_id(i2, "user")== true) && (Rs.get_admin().get_employee_id() != i2) && (i2 != 0)) {
				System.out.println("can delete");
				Db.execute_simple_query(delete_query);
				return 0;
			} else if(Rs.get_admin().get_employee_id() == i2) {
				System.out.println("noone is being used with this id");
				return 1;
			} else if(i2 == 0 || DbFunctions.check_id(i2, "user")==false){
				System.out.println("invalid user");
				return 2;
			}else {
				return 3;
			}
		} catch (Throwable e1) {
			e1.printStackTrace();
			return 3;
		}
			
	}
	
	/**
	 * This method is used to list all the users in the database
	 * @param Db - the database
	 * @return result set with the users
	 */
	//function that lists all the users in the system
	//working
	public ResultSet list_users(DbFunctions Db) {
			
		try {
			return Db.listUsers();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * This method is used to add a material to the database
	 * @param Db - the database
	 * @param i - integer with the id of the new material
	 * @param name - string with the name of the new material
	 * @param brand - string with the brand of the new material
	 * @param quantity - string with the quantity of the new material
	 * @param date - string with the date that the material is being added 
	 * @return integer: 0 that added correctly, 1- if the id is already in use, 2- if the id is invalid, 3 if error
	 */
	public int add_item_to_db(DbFunctions Db, int i,String name, String brand, int quantity, String date) {
		
		String query = "INSERT INTO material VALUES(" + i + ", '" +  name + "', '" +  brand + "', " + quantity + ", '" + date + "')"; ;
		
		try {
			if(DbFunctions.check_id(i, "material") == true) {
				return 1;
			}
			else if(i!=0) {
				
				Db.execute_simple_query(query);
				return 0;
				
			}
			else if(i==0) {
				return 2;
			}else {
				return 3;
			}
		} catch (Throwable e2) {
			e2.printStackTrace();
			return 3;
			
		}
		
	}
	/**
	 * This method is used to remove a material from the database
	 * @param Db - the database
	 * @param i2 - integer with the id that is to be removed
	 * @return integer: 0 if removed correctly, 1 if the id is invalid or is not being used, 2 if error
	 */
	public int remove_item_from_db(DbFunctions Db, int i2) {
		
		String del_mat = "DELETE FROM material WHERE id = " + i2 + ";";
		
		
		try {
			if((DbFunctions.check_id(i2, "material")== true) && i2 != 0) {
				System.out.println("can delete");
				Db.execute_simple_query(del_mat);

				return 0;
			} else {
				return 1;
			}
		} catch (Throwable e1) {
			e1.printStackTrace();
			return 2;
		}
		
		
		
		
	}
	/**
	 * This method is used to list the orders present in the database
	 * @param Db - the database
	 * @return string with the values of all of the orders present in the database
	 */

	public String[][] list_orders(DbFunctions Db){
		
		try {
			return Db.listOrders();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}




	
