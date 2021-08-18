/**
 * This class is used to perform the login
 * @author Refined Storage developers
 *
 */
public class temp_user_from_db {
	
	/**
	 * This constructor sets the passed parameters as variables
	 * @param the_name - name in use
	 * @param the_password - password in use
	 * @param e_id - id in use
	 * @param p - role in use
	 */
	public temp_user_from_db(String the_name, String the_password, int e_id, int p) {
		
		this.set_priority(p);
		this.set_employee_id(e_id);
		this.set_name(the_name);
		this.set_password(the_password);
		
	}
	
	private String name;

	private String password;
	
	private int employee_id;
	
	private int priority;
	
	/**
	 * This method sets the name with the n parameter 
	 * @param n the new name
	 */
	public void set_name(String n) {
		
		this.name = n;
		
	}
	/**
	 * This method is used to get the name that is in use
	 * @return the name that is in use
	 */
	public String get_name() {
		
		return this.name;
		
	}
	
	/**
	 * This method sets the password with the pass parameter
	 * @param pass the password to be used
	 */
	public void set_password(String pass) {
		
		this.password = pass;
		
	}
	/**
	 * This method is used to get the password that is in use
	 * @return the password that is in use
	 */
	public String get_password() {
		
		return this.password;
		
	}

	
	/**
	 * This method is used to set the employee id with the id parameter
	 * @param id - the id that is to be used
	 */
	public void set_employee_id(int id) {
		this.employee_id = id;
	}
	/**
	 * This method is used to get the employee id that is in use
	 * @return the id that is in use
	 */
	public int get_employee_id() {
		return this.employee_id;
	}
	
	/**
	 * This method defined the role that is in use
	 * @param p - the role is to be changed
	 */
	public void set_priority(int p) {
		this.priority = p;
	}
	/**
	 * This method is used to get the role that is in use
	 * @return the role that is in use
	 */
	public int get_priority() {
		return this.priority;
	}
}
