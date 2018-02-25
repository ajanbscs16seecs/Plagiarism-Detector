/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plagiarism.detector;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author Arif
 */
public class PlagiarismDetector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

                
        


    }
    
    public static void start(){
        
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        int result = fileChooser.showOpenDialog(new Component());
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        }
    }
    
    
    public static void printList(List<String> a){
        System.out.println();
        a.forEach((string) -> {
            System.out.print(string);
        });
        System.out.println();
    }
    
    public static String process(List<TextFile> allFiles,final TextFile file){
        
        String[] result=new String[1];
        result[0] ="NOTE: Erase the result for another operation.\nResult\n";
        
        
        //calculate frequency of test first....
        file.setFrequency(toWordsFrequency(file));
        
        allFiles.forEach((textFile)->{
            result[0]+= "\n\nFile: "+textFile.getTile()+"\n"+process(textFile,file);
        });
        return result[0];
    }
    
    public static String REGEX_WORD = "\\w+";
    public static String process(TextFile orignal,TextFile file){
        long startTime = System.nanoTime();
        orignal.setFrequency(toWordsFrequency(orignal));

        orignal.setNorm(calculateNorm(orignal.getFrequency()));
        file.setNorm(calculateNorm(file.getFrequency()));
        
        
        double dotproduct = dotproduct(orignal.getFrequency(),file.getFrequency());
        double angle =Math.acos(dotproduct/(orignal.getNorm()*file.getNorm()));
        
        double diff = (Math.PI/2)-angle;
        
        long endTime = System.nanoTime();
        int nanoseconds = (int) (endTime-startTime);
        return "matched: "+(diff/Math.PI)*100+"%"+"\nfound in "+nanoseconds+" nanoseconds";
    }
    
    public static Map<String,Integer> toWordsFrequency(TextFile file){
        Pattern pattern = Pattern.compile(REGEX_WORD);
        
        Matcher matcher = pattern.matcher(file.getContent());
        Map<String,Integer> tempmap= new HashMap<>();
        while (matcher.find()) {
            String temp = matcher.group();
            temp=temp.trim();
            temp = temp.toLowerCase();
            
            if (tempmap.containsKey(temp)) {
                tempmap.put(temp, tempmap.get(temp)+1);
            } else {
                tempmap.put(temp, 1);
            }
        }
        
        return tempmap;
    }

    public static double calculateNorm(Map<String,Integer> freq){
        double sumofsqrs = 0;
        for(Integer i:freq.values()){
            sumofsqrs+=Math.pow(i,2);
        }
        
        return Math.sqrt(sumofsqrs);
    }
    
    public static double dotproduct(Map<String,Integer> freq1,Map<String,Integer> freq2){
       
       double answer =0;
       for(String key:freq1.keySet()){
           if(freq2.containsKey(key)){
               answer+=(freq1.get(key)*freq2.get(key));
           }
       }
       return answer;
       
    }
}
