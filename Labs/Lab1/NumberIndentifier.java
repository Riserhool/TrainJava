package Lab1;

public class NumberIndentifier {

    public static boolean isRomanNumber(String number){
    switch (number.toUpperCase()){
            case "I":
                return true;
        //               break;

            case "II":
                return true;
        //               break;

            case "III":
                return true;
        //               break;

            case "IV":
                return true;
        //               break;

            case "V":
                return true;
        //              break;

            case "VI":
                return true;
        //               break;

            case "VII":
                return true;
        //              break;

            case "VIII":
                return true;
        //              break;

            case "IX":
                return true;
        //               break;

            case "X":
                return true;
        //               break;

            default:
                return false;
        //              break;

            }
        }

        
    public static boolean isArabicNumber(String number) {
    switch (number){
        case "1":
            return true;
    //      break;
        case "2":
            return true;
    //              break;

        case "3":
            return true;
    //               break;

        case "4":
            return true;
    //               break;

        case "5":
            return true;
    //                break;

        case "6":
            return true;
    //               break;

        case "7":
            return true;
    //               break;

        case "8":
            return true;
    //               break;

        case "9":
            return true;
    //               break;

        case "10":
            return true;
    //               break;

        default:
            return false;
    //               break;

        }
    
}
}