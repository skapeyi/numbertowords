package com.example;

/**
 * Created by Sammie on 6/20/2015.
 */

import java.util.Scanner;
public class numbertowords {
    public static String output;
    public static long numberToConvert1,numberToConvert;
    public static String[] ones= {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};
    public static String[] _tens={"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public static String[] thousands={"","Thousand", "Million", "Billion","Trillion","Quintillion","Sextillion"};


    public static void main(String[] args) {
        Scanner	scanner = new Scanner(System.in);
        System.out.println("Enter Number");
        numberToConvert1= scanner.nextLong();

        numberToWords2(numberToConvert1);
        System.out.println(output);

    }


    public static String numberToWords2(double numberToConvert12){

        if(numberToConvert12==0){					//Apply the rule to remove the zero numbers and exit the program
            output=ones[0];
            return output;
        }
        /**
         * Split the number into three digit groups... and store them in an array of
         * numbers, which will be later converted.individually*/
        int[] stringGroups= new int[7];
        numberToConvert= Math.abs(numberToConvert1);//converts negative numbers to positive equivalents
        /**
         * This for loop gets the number which was input
         * and then converts them to string groups.*/
        for(int i=0;i<7;i++){
            stringGroups[i] = (int) (numberToConvert%1000);
            numberToConvert/=1000;
        }

        /**
         * This for loop gets the string groups, from the above for loop and converts them to words,
         * by calling a method which converts the string groups to words
         * */
        String[] wordsToecho=new String[7];
        for(int i1=0;i1<7;i1++){

            wordsToecho[i1]=ConvertDigitGroupToWord(stringGroups[i1]);
            //System.out.println("String Group "+i1+ "= "+wordsToecho[i1]);


        }
        /**
         * Now that we have all the string groups have been individually turned to text
         * We can now reconstruct them to give one whole word, by appending the thousands, billions or millions
         */
        String combined = wordsToecho[0];
        boolean appendAnd;
        appendAnd = (stringGroups[0] > 0) && (stringGroups[0] < 100);
        for(int i=1;i<7;i++){
            if(stringGroups[i]!=0){
                String prefix = wordsToecho[i]+ " "+thousands[i]+" ";
                if(combined.length()!=0){
                    //prefix += appendAnd ? "and":", ";
                }
                combined= prefix+combined;
            }
        }
        if (numberToConvert1<0){
            combined = "Negative "+combined;
        }
        output=combined;
        System.out.println(output);//Print the converted words
        System.out.println("=================Next==================");
        main(wordsToecho);//go back to the beginning of the program

        return output;
    }

    private static String ConvertDigitGroupToWord(int digitGroup) {
        //Initialize the output text
        String output="";
        //determine the hundreds and the remainder
        int hundreds = digitGroup/100;
        int tensUnits = digitGroup%100;

        //Apply the hundreds rule
        if(hundreds!=0){
            output +=ones[hundreds]+ " Hundred";

            if(tensUnits!=0){
                output += " and ";
            }
        }

        //Determine the tens and units
        int tens=tensUnits/10;
        int units =tensUnits%10;

        //The tens rule
        /**
         * If then value after dividing by 10 is greater than 2, which means the tens unit is
         * greater than 20, then append the value e.g twenty, thirty etc. to the output
         * and then also from the remainder with modulus division, add the word to the output e.g thirteen
         * */
        if (tens>=2){
            output += " "+ _tens[tens];
            if(units!=0){
                output +=" " + ones[units];
            }
        }
        else if (tensUnits !=0)
            output += ones[tensUnits];


        return output;
    }



}

