package Lab1;

public class ArabicCalcs extends Calculations {
    char operation;
    int number1 = 0;
    int number2 = 0;
    int out = 0;

    public ArabicCalcs(char operation, int number1, int number2){
        this.operation = operation;
        if(operation == '+')
        {
            
            out = addition(number1, number2);
            
        }
        else if(operation == '-')
        {
            out = substraction(number1, number2);

        }
        else if(operation == '/')
        {
            out = division(number1, number2);

        }
        else if(operation == '*')
        {
            out = multiplication(number1, number2);

        }
        System.out.println("Вывод:" + out);
    }

    


}
