/**
 * This class has the logged user, the database in use, with some login type functions
 * @author Refined Storage developers
 *
 */
public class Refined_storage {
	
	private boolean user_logged = false;
	
	private static DbFunctions Db = new DbFunctions();
	
	private int user_prio = 0;
	
	private Employee emp_user;
	
	private Admin adm_user;
	
	
	/**
	 * This method is used to get the employee in use
	 * @return the employee in use
	 */
	public Employee get_employee() {
		return emp_user;
	}
	/**
	 * This method is used to get the admin in use
	 * @return the admin in use
	 */
	public Admin get_admin() {
		return adm_user;
	}
	/**
	 * This method is used to set the admin that is being used
	 * @param id the id that is to be used
	 * @param name the name that is to be used
	 * @param pass the password that is to be used
	 * @param prio the role that is to be used
	 */
	public void set_admin(int id,String name, String pass, int prio) {
		Admin adm = new Admin(id, name, pass, prio);
		this.adm_user = adm;
	}

	/**
	 * This method is used to set the user(admin, employee) logged in
	 * @param state true if the user is going to be used, false if not
	 */
	public void set_user_logged(Boolean state) {
		
		this.user_logged = state;
		
	}
	/**
	 * This method is to see if the user is being used or not
	 * @return true if it is being used, false if not
	 */
	public boolean get_user_logged() {
		
		return this.user_logged;
		
	}
	/**
	 * This method is used to set the role of the user in use
	 * @param p the role to be set(1 for admin, 2 for employee) 
	 */
	public void set_user_prio(int p) {
		
		this.user_prio = p;
		
	}
	/**
	 * This method is used to get the role of the user that is being used
	 * @return the role of the user being used (1 admin, 2 employee)
	 */
	public int get_user_prio() {
		
		return this.user_prio;
		
	}
	
	/**
	 * This method is used to get the database in use
	 * @return the database
	 */
	public DbFunctions get_Db() {
		return Db;
	}

	/**
	 * This method is used to perform the login. The parameters user_id and password are
	 * going to be compared to those in the database
	 * @param user_id the user id  
	 * @param password the user password
	 * @return true if the login was succesfull, false if not
	 */
	public boolean perform_a_login(int user_id, String password) {
		
		try {
			DbFunctions.com();
			if(Db.login(user_id, password)!=null) {
				this.set_user_prio(Db.get_prio_from_ID(user_id));
				
				String aux[] = Db.getName_and_pw_FromID(user_id);
				
				if(this.get_user_prio() == 1) {
					this.adm_user = new Admin(user_id, aux[0], aux[1], this.get_user_prio());
					System.out.println(this.adm_user.get_name() + aux[1]);
				}else if(this.get_user_prio() == 2) {
					this.emp_user = new Employee(user_id, aux[0], aux[1], this.get_user_prio());
				}
				
				return true;
				
			}else {
				return false;
			}
		}  catch (Throwable e1) {
			System.err.println(e1.getMessage());
			return false;
		}
		
	}
	/**
	 * This method is used to perform the logout
	 */
	public void perform_a_logout() {
		
		this.adm_user = null;
		this.emp_user = null;
		
		this.set_user_prio(0);
		
	}
}
