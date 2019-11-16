import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataStore {
	
	public static String create(String path ,String key, String value) throws Exception {
		String filePath = "";
		if (path != null) {
			filePath = path;
		} else {
			filePath = System.getProperty("java.class.path");
			filePath = filePath.substring(0, filePath.indexOf("data-store") + 10);
			filePath = filePath + "\\src\\main\\java\\files";
		}
		
		Path files = FileSystems.getDefault().getPath(filePath);
	    Files.createDirectories(files); 
	    
	    filePath = filePath + "\\" + key;
	    
	    try {
	    	DataStore.getFileContent(filePath);
	    	throw new Exception("Key already exists");
	    } catch (NoSuchFileException exception) {
	    	FileOutputStream out = new FileOutputStream(filePath, false);
	        OutputStreamWriter streamWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
	        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
	        bufferedWriter.write(value);
	        bufferedWriter.close();
	        
	    }
	   
		return filePath;
	}
	
	public static String create(String key, String value) throws Exception {
		return create(null, key, value);
	}
	
	
	public static void delete(String key) throws Exception {
		String filePath = "";
		filePath = System.getProperty("java.class.path");
		filePath = filePath.substring(0, filePath.indexOf("data-store") + 10);
		filePath = filePath + "\\src\\main\\java\\files\\" + key;
		try {
			File file = new File(filePath); 
			file.delete();
		} catch (Exception e) {
			throw new Exception("File not found!");
		}
        
        return;
	}
	
	public static String read(String key) throws Exception {
		String filePath = "";
		filePath = System.getProperty("java.class.path");
		filePath = filePath.substring(0, filePath.indexOf("data-store") + 10);
		filePath = filePath + "\\src\\main\\java\\files\\" + key;
		String content = "";
		
		try {
			content = DataStore.getFileContent(filePath);
		} catch(Exception e) {
			throw new Exception("Unable to Detect file");
		}

		return content;
	}

	private static String getFileContent(String filePath) throws IOException {
		String content = ""; 
		
		List <String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		for (String ct: lines) {
			content = content + ct;
		}
	    
	    return content;
	    
	}

}
