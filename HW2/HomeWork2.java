package HW2;
import java.util.Scanner;

// Переворот числа
public class HomeWork2
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.printf("Введите число: ");
        int num = in.nextInt();
        int count = 0;
        int length = 1;
        for(count = num; count > 10; count = count / 10)
        {
            length++;
        }
        
       // System.out.println(length);
        double n = 0;
        for(int m = 1; m < (length + 1); m++)
        {
            n = num%(Math.pow(10, m))/(Math.pow(10, (m - 1)));
            System.out.print(Math.round(Math.floor(n)));
        }


        System.out.print("\n");
        in.close();
    }
}