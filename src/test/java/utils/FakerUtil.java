package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FakerUtil
{
    static  Faker f= new Faker();

    public static String generateFirstName(){
        return f.name().firstName();
    }

    public static String generateMiddleName(){
        return f.name().firstName();
    }

    public static String generateLastName(){
        return f.name().lastName();
    }

    public static String generateBirthDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new SimpleDateFormat("yyyy-MM-dd").format(f.date().birthday());
    }

    public static String genrateDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new SimpleDateFormat("yyyy-MM-dd").format(f.date().between(new Date(0), new Date()));
    }

    public static String generateCountry(){
        return f.demographic().demonym();
    }

    public static String getGender(){
        return f.options().option("Male", "Female");

    }

    public static String getempID(){
        return f.number().digits(5);
    }


}
