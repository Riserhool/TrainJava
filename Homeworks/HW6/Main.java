import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoCallable{
    String name() default "default_string";

    // Writes at Std out
    // Writes at Std error

}


public class Main {

    @AutoCallable
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/denis/Документы/VSC/Java/Homeworks/HW6/config.txt"));
            String line = reader.readLine();
            if (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            else{
                System.out.println("Пустой файл!");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





