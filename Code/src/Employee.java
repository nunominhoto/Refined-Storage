
/**
 * This class represents the employee, and has all the employee functions in it
 * @author Refined Storage developers
 *
 */
public class Employee {

	private int employee_id;
	
	private String name;
	
	private String pw;
	
	private int priority;
	
	
	/**
	 * This constructor class sets the passed parameters as variables 
	 * @param id - the id that is being used
	 * @param user_name - the name that is being used
	 * @param pw - the password that is being used
	 * @param prio - the role that is being used (admin, employee)
	 */
	public Employee(int id, String user_name, String pw, int prio) {
		
		set_employee_id(id);
		
		set_name(user_name);
		
		set_priority(prio);
		
		set_pw(pw);
		
	}
	/**
	 * This method sets the current employee id  
	 * @param id - the id that is to be used
	 */
	public void set_employee_id(int id) {
		
		this.employee_id = id;
		
	}
	/**
	 * This method is used to get the current id that is being used
	 * @return the id that is being used
	 */
	public int get_employee_id() {
		return this.employee_id;
	}
	/**
	 * This method sets the current employee name 
	 * @param new_name - the name that is to be used
	 */
	public void set_name(String new_name) {
		this.name = new_name;
	}
	/**
	 * This method is used to get the current name that is being used
	 * @return the name that is being used
	 */
	public String get_name() {
		return this.name;
	}
	/**
	 * This method sets the current employee password
	 * @param new_pw - the password that is to be used
	 */
	public void set_pw(String new_pw) {
		this.pw = new_pw;
	}
	/**
	 * This method is used to get the current password that is being used
	 * @return the password that is being used 
	 */
	public String get_pw() {
		return this.pw;
	}
	/**
	 * This method is used to get the current role of the employee
	 * @return the role that is currently in use
	 */
	public int get_priority() {
		return this.priority;
	}
	/**
	 * This method sets the current employee role
	 * @param p the role that is to be used
	 */
	public void set_priority(int p) {
		this.priority = p;
	}
		
	

	/**
	 * This method is used to look for a determined item in the database. Only one parameter of id, name, brand can be true at a time
	 * @param Db - the database
	 * @param search_by_id - true if wanted search by id, false if not
	 * @param search_by_name - true if wanted search by name, false if not
	 * @param search_by_brand - true if wanted search by brand false if not
	 * @param text - the value that wants to be searched
	 * @return string with all the materials that correspond with the text parameter
	 */
	public String[][] search_item(DbFunctions Db, boolean search_by_id, boolean search_by_name, boolean search_by_brand, String text) {
		
		try {
			if(search_by_id && !search_by_name && !search_by_brand) {
				String[][] s = Db.searchByID(text);
				return s;
			}
			else if(!search_by_id && search_by_name && !search_by_brand) {
				String[][] s = Db.searchByName(text);
				return s;
			}
			else if(!search_by_id && !search_by_name && search_by_brand) {

				String[][] s = Db.searchByBrand(text);
				return s;
			}else {
				
				return null;
				
			}
			
		} catch (Throwable e1) {
			System.out.println(e1.getMessage());
			return null;
		}
		
	}
	
	/**
	 * This method is used to list all the values of the table material in the database 
	 * @param Db - the database
	 * @return string with all the values of the table materialS
	 */
	public String[][] list_inventory(DbFunctions Db) {
		
		try {
			String[][] s = Db.listMaterials();
			return s;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	
	/**
	 * This method is used to update the quantity in the table material of the wanted material
	 * @param Db - the database
	 * @param item_id - the id of the material 
	 * @param quantity - the quantity that is to be removed or added
	 * @param add - true if quantity is to be added, false if not
	 * @param remove - true if quantity is to be removed, false if not
	 * @return 0 if quantity to be removed is less then quantity existent, 1 if no errors
	 */
	public int manage_inventory(DbFunctions Db, int item_id, int quantity, boolean add, boolean remove) {
		
		try {
			int db_qty = Db.getQuantityFromID(item_id);
			if(add && !remove) {
				Db.updateQuantityFromID(item_id, db_qty + quantity);
			}
			else if((!add && remove) && (db_qty >= quantity)){
				Db.updateQuantityFromID(item_id, db_qty - quantity);
			}
			else if((!add && remove) && (db_qty < quantity)){
				return 0;
			}
		} catch (Throwable e1) {
			System.out.println(e1.getMessage());
		}
		return 1;
	}
	
	/**
	 * This method is used to change the password in the user database
	 * @param Db - the database
	 * @param old_password - the password that is in the database 
	 * @param new_password - the desired new password
	 * @param new_password_confirmation - the desired new password
	 * @return 0 if it was successful, 1 if the new password is the same as the old password,
	 * 2 if the new password and the password confirmation are not the same, 3 if the old password
	 *  is incorrect, 4 if error 
	 */
	public int change_password(DbFunctions Db, String old_password, String new_password, String new_password_confirmation) {
		try {
		
			 if(this.get_pw().equals(old_password)) {
				 
				if(new_password.equals(new_password_confirmation)) {
					if(old_password.equals(new_password) == false) {
						System.out.println(new_password);
						Db.execute_simple_query("UPDATE user SET password ='" + new_password + "' WHERE id=" + this.get_employee_id());
						this.set_pw(new_password);
						return 0;
					}else {
						return 1;
					}
				}else {
					return 2;
				}
			 }else {			
				 return 3;				 
			 }
		} catch (Throwable e1) {
			e1.printStackTrace();
			return 4;
		}	
		
	}
	
	
	
}
