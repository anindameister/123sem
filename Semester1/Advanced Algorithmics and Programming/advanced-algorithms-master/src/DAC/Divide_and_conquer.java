/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAC;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Divide_and_conquer {
    
    public  ArrayList<String> words= new ArrayList<>();
    public static String solGlo= new String();
    private int count=0;
    
    
    public Divide_and_conquer(ArrayList<String> words) {
        this.words  =words;
        count=this.words.size();
    }
    
    public int[] divide_and_conquer(String wordText)
    {
        try {
            
        
         int l=0; //current state of line size
         int m=10; //size of the line with
         int lc=0;
         int totalLineCost=0;//total cost
         int[] lineCostArray=new int[count]; //each line cost
         String solution= "";
         int lcac=0;//count variable to store line cost array
         int tempLenth=0;
         int stringLen=wordText.length();
         int loopCount=stringLen/m;
         int remindr=stringLen%m;
         if(remindr>0)
         {
          loopCount++;
         }
          ArrayList<String> devided = new ArrayList<String>();;
          int startingPos=0;
         
          int arrayCount=0;
         for (int i = 1; i <= loopCount; i++) {
  
             if(i==loopCount)
            {  //,stringLen
                devided.add(arrayCount, wordText.substring(startingPos));
            }  
             else{
               devided.add(arrayCount,wordText.substring(startingPos, m));
             }
             startingPos +=m;
             arrayCount++;
         }
         int countOfWordCurrent=0;
         String[] spilitedArray;
         int countOfWordsOfEachLine;
         String lastWord;
         String actualWord="";

         //Logic
         for (int i = 0; i < loopCount; i++) {
            spilitedArray= devided.get(i).split("\\s+");//each line string wil be inserted to array
            countOfWordsOfEachLine= spilitedArray.length;
            countOfWordCurrent=countOfWordCurrent+(countOfWordsOfEachLine-1);
            lastWord=spilitedArray[countOfWordsOfEachLine-1]; //got the last word
            
            actualWord=words.get(countOfWordCurrent);//get actual comparing word
                
            //resizing the words by considering line space limit
            if(devided.get(i).length()>m)
            {
            //have to add the last from string from devided[i] to devided[i+1];
           // devided[i+1]=addString(devided[i+1],lastWord,0); //we adding to next line if it is not fitting
             devided.add(i+1,addString(devided.get(i+1),lastWord,0));
            //have to remove the last from string from devided[i[;
             String original = devided.get(i+1);
             String newString = original.replace(" "+lastWord,"");
             devided.add(i,newString);
             
           
             spilitedArray= devided.get(i).split("\\s+");//each line string wil be inserted to array
             countOfWordsOfEachLine= spilitedArray.length;//this is actually last word count of the line
             lastWord=spilitedArray[countOfWordsOfEachLine-1]; //got the last word
            }
            
            if(actualWord.length()!=lastWord.length())
            {
                //come here to check **********************
              devided.add(i+1,addString(devided.get(i+1),lastWord,0));
              //have to remove the moved string from devided[i[;
              String original = devided.get(i);
              String newString = original.replace(" "+lastWord,"");
              devided.add(i,newString);
            }
           
//            countOfWordCurrent=countOfWordCurrent+(countOfWordsOfEachLine-1); //storing word count to maintain its state
            
         }

         

//************************
         System.out.println(totalLineCost );
         
         return lineCostArray;
         
         } catch (Exception e) {
                System.out.print(e.getMessage());
         }
        return null;
     }
    
    
    public String addString(String str, String ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }
        
}
