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

        String splt = " ";
//Input
// Совместить сплит и метод - done
        for (int i = 0; i < Input.length(); i++) {
            InpChar[i] = Input.charAt(i);
            if (InpChar[i] == '+') {
                splt = "\\+";
            }
            if (InpChar[i] == '-') {
                splt = "-";
            }
            if (InpChar[i] == '*') {
                splt = "\\*";
            }
            if (InpChar[i] == '/') {
                splt = "/";
            }
        }
//Разделение вводимой строки на 2 значения, избавляемся от пробелов
            String[] numbers = Input.split(splt);
            numbers[0] = numbers[0].trim();
            numbers[1] = numbers[1].trim();

//Проверка на Араб\Римские
            boolean num1rom = NumberIndentifier.isRomanNumber(numbers[0]);
            boolean num2rom = NumberIndentifier.isRomanNumber(numbers[1]);
            boolean num1arab = NumberIndentifier.isArabicNumber(numbers[0]);
            boolean num2arab = NumberIndentifier.isArabicNumber(numbers[1]);
            
//Не прошел проверку
            if((num1rom != num2rom) || (num1arab != num2arab)){
                System.out.println("Неверный формат чисел.");
            }


//Конструктор Римские
            else if(num1rom == true){

                RomanCalcs answer = new RomanCalcs(numbers[0], numbers[1]);
                int out = 0;

                if(splt == "\\+")
                {
                    out = answer.addition(answer.first, answer.second);
                }
                else if(splt == "-")
                {
                    out = answer.substraction(answer.first, answer.second);
                }
                else if(splt == "/")
                {
                    out = answer.division(answer.first, answer.second);
                }
                else if(splt == "\\*")
                {
                    out = answer.multiplication(answer.first, answer.second);
                }
        
                if(out < 0){
                    System.out.println("Вывод:Неположительный результат");
                }
                else{
                    String c = answer.romanSolution(out);
                    System.out.println("Вывод:" + c);
                }                

            }
    
            //Перевод string в int 2 составляющие для арабских
            else if(num1arab ==  true){
                // NumberIndentifier n = new NumberIndentifier();
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);   


            //Конструктор Арабские
            ArabicCalcs answer = new ArabicCalcs(splt, num1, num2);
                if(splt == "\\+")
                {
                    
                    System.out.println("Вывод: " + answer.addition(num1, num2)); 
                    
                }
                else if(splt == "-")
                {
                    System.out.println("Вывод: " + answer.substraction(num1, num2)); 
                    
                }
                else if(splt == "/")
                {
                    System.out.println("Вывод: " + answer.division(num1, num2)); 
                    

                }
                else if(splt == "*")
                {
                    System.out.println("Вывод: " + answer.multiplication(num1, num2)); 
                }

            }
        }
        //сделать вывод данных из решений  здесь, арабские готовы, римские тоже
        //работа с пробелом done
    }

