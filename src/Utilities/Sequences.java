package Utilities;

import Exceptions.ClassDoesNotExist;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sequences {
    private static Map<String, Integer> classSequence = new HashMap<>();
    private static String fileToload = "Data/ClassSequences.csv";
    private Sequences(){}

    static{
        try(Scanner f = new Scanner(new File(fileToload))) {
            String line;
            while(f.hasNextLine())
            {
                line = f.nextLine();
                String[] parts = line.split(",");
                classSequence.put(parts[0], Integer.valueOf(parts[1].strip()));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveToFile()
    {
        try(FileWriter f = new FileWriter(fileToload))
        {
            for(Map.Entry<String, Integer> elem : classSequence.entrySet())
            {
                f.write(elem.getKey() + ", " + elem.getValue() + "\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String nextVal(String className)
    {
        Integer nextval = classSequence.get(className);

        if(nextval == null)
            throw new ClassDoesNotExist("numele clasei nu este valid");

        nextval++;
        classSequence.put(className, nextval);

        if(className.equalsIgnoreCase("Depozit"))
            return "d" + nextval.toString();
        if(className.equalsIgnoreCase("Magazin"))
            return "m" + nextval.toString();

        return nextval.toString();

    }
}
