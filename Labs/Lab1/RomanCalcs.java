package Lab1;

public class RomanCalcs extends Calculations {
    
    int out = 0;
    int first = 0;
    int second = 0;

    public RomanCalcs(String number1, String number2){

        switch (number1.toUpperCase()){
            case "I":
            {
            first = 1;
            break;
            }
            case "II":
            {
            first = 2;
            break;
            }
            case "III":
            {
            first = 3;
            break;
            }
            case "IV":
            {
            first = 4;
            break;
            }
            case "V":
            {
            first = 5;
            break;
            }
            case "VI":
            {
            first = 6;
            break;
            }
            case "VII":
            {
            first = 7;
            break;
            }
            case "VIII":
            {
            first = 8;
            break;
            }
            case "IX":
            {
            first = 9;
            break;
            }
            case "X":
            {
            first = 10;
            break;
            }
            default: {break;}
        }
        switch (number2.toUpperCase()){
            case "I":
            {
            second = 1;
            break;
            }
            case "II":
            {
            second = 2;
            break;
            }
            case "III":
            {
            second = 3;
            break;
            }
            case "IV":
            {
            second = 4;
            break;
            }
            case "V":
            {
            second = 5;
            break;
            }
            case "VI":
            {
            second = 6;
            break;
            }
            case "VII":
            {
            second = 7;
            break;
            }
            case "VIII":
            {
            second = 8;
            break;
            }
            case "IX":
            {
            second = 9;
            break;
            }
            case "X":
            {
            second = 10;
            break;
            }
            default: {break;}
        }
    }

    public String romanSolution(int arabNumeral) {
        String[] romanAll = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", 
                "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", 
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", 
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", 
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", 
                "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String q = romanAll[arabNumeral];
        return q;
    }
}