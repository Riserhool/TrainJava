package HW2;
import java.util.Scanner;

//Сортировка чисел плюс медиана 

public class HomeWork3
{
 
 
   public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.printf("Введите количество чисел: ");
        int m = in.nextInt(); // Вводим количество цифр
        int [] array = new int[m];
        for(int count = 0; count < m; count++) // Вводим цифры по одному
        {
            System.out.printf("Введите " + (count + 1) + " число \n");
            array[count] = in.nextInt();
        } 
        int var = 0; // Изменяемый variable для хранения перемещаемого значения
        
        // Сортируем
        for (int a = 0; a < (m - 1); a++)
        {
            for(int n = 0; n < (m - 1 - a); n++)
            {
                if(array[n] > array[n + 1])
                {
                    var = array[n +1];
                    array[n + 1] = array[n];
                    array[n] = var;
                    var = 0;
                }
            }
        }

        

        // Выводим сортировку

        System.out.printf("Вывод:\n");
        for (int i = 0; i < m; i++)
        {
            System.out.print(array[i] + " " );
        }
        System.out.printf("\n");



        // Считаем и выводим медиану

        if ((m % 2) != 0) //Проверка на четность
        {
            System.out.println(array[m / 2]); //Если нечетное, берем среднее
        }
        else //Если четное, считаем среднее между двух значений
        {
            float d = array[(m - 1) / 2] + array[m / 2];
            System.out.println(d / 2);
        }
            
        in.close();
    }
}
