package Lab1;

import java.util.Scanner;

//считывание с консоли арифметического выражения
//определение системы счисления
//создание подходящего обьекта для проведения расчетов в зависимости от системы
//вывод в консоль результата расчетов

public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Ввод: ");
        String Input = in.nextLine();
        char[] InpChar = new char[10];
        char method = ' ';
        String splt = " ";
// Совместить сплит и метод
        for (int i = 0; i < Input.length(); i++) {
            InpChar[i] = Input.charAt(i);
            if (InpChar[i] == '+') {
                method = '+';
                splt = "\\+";
            }
            if (InpChar[i] == '-') {
                method = '-';
                splt = "-";
            }
            if (InpChar[i] == '*') {
                method = '*';
                splt = "\\*";
            }
            if (InpChar[i] == '/') {
                method = '/';
                splt = "/";
            }
        }
            String[] numbers = Input.split(splt);

            boolean num1rom = NumberIndentifier.isRomanNumber(numbers[0]);
            boolean num2rom = NumberIndentifier.isRomanNumber(numbers[1]);
            boolean num1arab = NumberIndentifier.isArabicNumber(numbers[0]);
            boolean num2arab = NumberIndentifier.isArabicNumber(numbers[1]);
            

            if((num1rom != num2rom) || (num1arab != num2arab)){
                System.out.println("Неверный формат чисел.");
            }

            else if(num1rom == true){


                RomanCalcs answer = new RomanCalcs(method, numbers[0], numbers[1]);
            }

            else if(num1arab ==  true){

                NumberIndentifier n = new NumberIndentifier();
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);                

               ArabicCalcs answer = new ArabicCalcs(method, num1, num2);
               int answer2 = answer.addition(num1, num2);
               System.out.println(answer2);
            }
        }
        //сделать вывод данных из решений  здесь
        //работа с пробелом
    }

