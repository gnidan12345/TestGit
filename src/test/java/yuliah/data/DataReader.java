package yuliah.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filepath));

        //convert String to HashMap (Jackson databind)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //we create list with hashmaps {map, map1}

    }
}