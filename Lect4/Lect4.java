// package Lect4;

// public class Lect4
// {
//     public static void main(String[] args)
//     {
//         int denominator = 0;
//         try
//         {
//             double res = Main.devide(10.0, denominator);
//             System.out.println("all ok");
//         }
//         catch (ArithmeticException ae)
//         {
//             ae.printStackTrace();
//         }
//         catch(RuntimeException re)
//         {
//             re.printStackTrace();
//             System.err.println("Ай ай ай");
//         }
//         System.out.println("I'm here");
    
// //        int[] arr = {2,5,6,3};
//   //      for(int i=0; i < 5; i++)
//     //    {
//       //      System.out.println(arr[i]);
//         //}
//     }


//     public static int devide(int num, int denom){
//         if(denom==100){
//             throw new RuntimeException("cannot deivd 100");
//             }
//         return num/denom;
//     }
// }
