import jdk.jfr.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ReadName {
    private HashMap <String, String> config = new HashMap<>();
    private final File filePath = new File("src/Configuration") ;
        private static ReadName instance;

    private void Configuration() throws IOException{
        List <String> lines = Files.readAllLines(Paths.get(String.valueOf(filePath)));
        for (String line : lines) {
            String [] parts = line.split(":");
            config.put(parts[0], parts[1]);
        }
    }

    public static ReadName getInstance (){
        if (instance == null) {
            try {
                instance = new ReadName();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        } return instance;
    }
    private void saveConfiguration (){
        String configData = "";
        for(String key: config.keySet()){
            configData += key + ":" + config.get(key) + "\n";
        } try {
            FileWriter fileWriter = new FileWriter(new File(String.valueOf(filePath)), false);
            fileWriter.write(configData);
            fileWriter.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void save(String key, String value){
        config.put(key, value);
        saveConfiguration();
    }
    public String read (String key){
        if(config.keySet().contains(key)){
            return config.get(key);
        } return "key not found";
    }
}
