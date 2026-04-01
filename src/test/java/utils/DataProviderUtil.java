package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataProviderUtil {


    @DataProvider(name="loginData")
    public static Object[][] getData() throws IOException {
        return ExcelUtils.ReadFromExcel("loginData");
    }

    public static Map<String,String> employeeDetails() throws IOException {
        Random R=new Random();
        Map<String, String> m= new HashMap<>();
        m.put("firstName", FakerUtil.generateFirstName());
        m.put("middleName",FakerUtil.generateFirstName());
        m.put("lastName",FakerUtil.generateFirstName());
        m.put("gender",FakerUtil.getGender());
        m.put("dob",FakerUtil.generateBirthDate());
        m.put("country", FakerUtil.generateCountry());
        m.put("empID", FakerUtil.getempID());

        List<Object> jobTitles=ExcelUtils.ExceltoList("jobTitles");
        m.put("jobTitle", String.valueOf(jobTitles.get(R.nextInt(jobTitles.size()))));

        List<Object> jobCategories=ExcelUtils.ExceltoList("jobCategory");
        m.put("jobCategory", String.valueOf(jobCategories.get(R.nextInt(jobCategories.size()))));

        List<Object> subUnits=ExcelUtils.ExceltoList("subUnits");
        m.put("subUnit", String.valueOf(subUnits.get(R.nextInt(subUnits.size()))));

        List<Object> employmentStatus=ExcelUtils.ExceltoList("employmentStatus");
        m.put("employmentStatus", String.valueOf(employmentStatus.get(R.nextInt(employmentStatus.size()))));





        return m;

    }



}
