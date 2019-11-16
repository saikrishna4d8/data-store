
import org.junit.jupiter.api.Test;

public class DataStoreTest {
	
	@Test
	public void testCreate() throws Exception {
		DataStore.create("student3", "{ \"id\": 3, \"name\": \"Sai Krishna\" }");
		
		DataStore.create("C:\\Users\\sai\\eclipse-workspace","student4", "{ \"id\": 4, \"name\": \"Sai Krishna\" }");
		
	}
	
	@Test
	public void testRead() throws Exception {
		System.out.println(DataStore.read("student3"));
				
	}
	
	@Test
	public void testDelete() throws Exception {
		DataStore.delete("student1");
				
	}
	
}
