
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCases {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void DefaultLogin() {
		Refined_storage Rs = new Refined_storage();
		int prio;
		if(Rs.perform_a_login(1, "0000")){
		prio = Rs.get_user_prio();
		}
		else prio =0;
		assertEquals(1, prio);
		
	}
	
	@Test
	void AddUser() {
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		adm.add_user(Db, "employee", 55, "Teste", "0000");
		String[] user = Db.getName_and_pw_FromID(55);
		assertEquals("Teste", user[0]);
		assertEquals("0000", user[1]);
	}
	
	@Test
	void RemoveUser() {
		Refined_storage Rs = new Refined_storage();	
		Rs.perform_a_login(1, "0000");	
		Rs.get_admin().add_user(Rs.get_Db(), "employee", 60, "Teste", "0000");
		
			
		//if the user is removed, the function returns the value 0
		assertEquals(0, Rs.get_admin().rem_user(Rs.get_Db(), 60	, Rs));
		
		String[] user = Rs.get_Db().getName_and_pw_FromID(60);
		
		assertEquals(user, null);
		
	}

	@Test
	void AddMaterial() {
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		
		adm.add_item_to_db(Db, 800,"teste", "teste2", 3, "19 01 2021");
		String test = Db.get_material_from_ID(800);
		assertEquals("teste", test);
	}
	
	@Test
	void RemoveMaterial() {
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		
		adm.remove_item_from_db(Db, 800);
		String test = Db.get_material_from_ID(800);
		assertEquals(null, test);
	}
	
	
	@Test
	void AddQuantity() throws Throwable {
	
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		adm.add_item_to_db(Db, 66 ,"teste", "teste2", 3, "19 01 2021");
		int quantity = 6;
		Db.updateQuantityFromID(66, quantity);
		int db_qty = Db.getQuantityFromID(66);
		assertEquals(6, db_qty);
		adm.remove_item_from_db(Db, 66);
	}
	
	@Test
	void RemoveQuantity() throws Throwable {
	
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		adm.add_item_to_db(Db, 67 ,"teste", "teste2", 3, "19 01 2021");
		int quantity = 1;
		Db.updateQuantityFromID(67, quantity);
		int db_qty = Db.getQuantityFromID(67);
		assertEquals(1, db_qty);
		adm.remove_item_from_db(Db, 67);
	}
	
	@Test
	void SearchbyName() throws Throwable {
	
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		
		adm.add_item_to_db(Db, 35,"teste", "teste2", 3, "19 01 2021");
		String[][] l = Db.searchByName("teste");
		
		assertEquals("35", l[1][0]);
		assertEquals("teste", l[1][1]);
		assertEquals("teste2", l[1][2]);
		assertEquals("3", l[1][3]);
		assertEquals("19 01 2021", l[1][4]);
		adm.remove_item_from_db(Db, 35);
	}
	
	@Test
	void SearchbyId() throws Throwable{
	
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		
		adm.add_item_to_db(Db, 35,"teste", "teste2", 3, "19 01 2021");
		String[][] l = Db.searchByID("35");
		
		assertEquals("35", l[1][0]);
		assertEquals("teste", l[1][1]);
		assertEquals("teste2", l[1][2]);
		assertEquals("3", l[1][3]);
		assertEquals("19 01 2021", l[1][4]);
		adm.remove_item_from_db(Db, 35);
	}
	
	@Test
	void SearchbyBrand() throws Throwable{
	
		DbFunctions Db = new DbFunctions();
		Admin adm = new Admin(1, "Admin", "0000", 1);
		
		adm.add_item_to_db(Db, 35,"teste", "teste2", 3, "19 01 2021");
		String[][] l = Db.searchByBrand("teste2");
		
		assertEquals("35", l[1][0]);
		assertEquals("teste", l[1][1]);
		assertEquals("teste2", l[1][2]);
		assertEquals("3", l[1][3]);
		assertEquals("19 01 2021", l[1][4]);
		adm.remove_item_from_db(Db, 35);
	}
	
	@Test
	void ChangePassword() {
		DbFunctions Db = new DbFunctions();
		Refined_storage Rs = new Refined_storage();	
		
		Rs.perform_a_login(1, "0000");	
		// the method should return 0 when done successfully 
		assertEquals(0, Rs.get_admin().change_password(Db, "0000", "1234", "1234"));
		String[] user = Rs.get_Db().getName_and_pw_FromID(1);
		assertEquals("1234", user[1]);
		assertEquals(0, Rs.get_admin().change_password(Db, "1234", "0000", "0000"));
		String[] user2 = Rs.get_Db().getName_and_pw_FromID(1);
		assertEquals("0000", user2[1]);
	}
}
