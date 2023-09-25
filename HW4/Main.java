package HW4;

public class Main {}
    public static void main(String[] args) {}
 /*    Разработать класс StringDataValidator, который производит проверку на идентичность двух
        передаваемыъ строк. Класс содержит один метод boolean validate(String first, string (()second).
                В началеметода д.б. произведеа проверка на то, что обе строки непустые и не null, иначе
                    бросается EmptyDataException. Потом идет проверка, что обе строки несодержат никаких символов кроме английского алфавита и цифр. 
                Потом идет сравнение строк.  
        
        Также необходимо разработать иерархию исключений:
        -DataException - родительский exception, наследуется от runtime exception.
        -EmptyDataexception бросается, когда переданная строка null или пустая, наследуется от DataException
        -UnknownCharacterException бросается, когда строка содержит символы кроме английского и цифр, наследуется от Data Exception.
        
        Провести проверку сценариев:
        1. передать две одинаковых строки, должно вернуться true;
        2. Передать две разных строки должно вернуться false;
        3. Передать две одинаковых строки должно вызваться исключение UnknownCharacterException.
        4. Передать одну пустую строку и одну произвольную должно вызваться исключение EmptyDartaException

        Отловить exceptions в блоке try catch прив озникновении EmptyDataEcxeption вывести stack trace exception и в system error надпись о том что сделано неправильно.
        При UnknownCharacterException сделать то же самое но выводить в system out.
 */