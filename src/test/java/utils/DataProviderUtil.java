package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataProviderUtil {


    @DataProvider(name="loginData")
    public static Object[][] getData() throws IOException {
        return ExcelUtils.ReadFromExcel("loginData");
    }

    public static Map<String,String> employeeDetails(){
        Map<String, String> m= new HashMap<>();
        m.put("firstName", FakerUtil.generateFirstName());
        m.put("middleName",FakerUtil.generateFirstName());
        m.put("lastName",FakerUtil.generateFirstName());
        m.put("gender",FakerUtil.getGender());
        m.put("dob",FakerUtil.generateBirthDate());
        m.put("country", FakerUtil.generateCountry());
        m.put("empID", FakerUtil.getempID());

        return m;

    }



}
