package SimpleJsonPackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SimpleaJsonClass {
    public static  void main(String[] args) throws IOException, ParseException {
        JSONParser jsonparser= new JSONParser();
        FileReader file = new FileReader(".\\JsonFolder\\JsonPathFile");
        Object obj = jsonparser.parse(file);
        JSONObject empjsonobject = (JSONObject)obj;
        String fname= (String)empjsonobject.get("name");
        String fsal = (String)empjsonobject.get("Salary");
        System.out.println("name is:" + fname);
        System.out.println("salar is :" +fsal);

        JSONArray array = (JSONArray) empjsonobject.get("address");
        for(int i= 0; i<array.size(); i++){
            JSONObject ADDRESS = (JSONObject) array.get(i);
            String st = (String) ADDRESS.get("street");
            String dno = (String) ADDRESS.get("doorno");
            String cty= (String) ADDRESS.get("city");
            System.out.println("street is:" + st);
            System.out.println("door no is : " + dno);
            System.out.println("city is :" + cty);

        }

    }
}

