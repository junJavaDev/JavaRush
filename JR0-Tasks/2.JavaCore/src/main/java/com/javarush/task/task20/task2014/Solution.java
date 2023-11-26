package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {
        System.out.println(new Solution(4));
        try (   FileInputStream fis = new FileInputStream("D:\\txt.txt");
                FileOutputStream fos = new FileOutputStream("D:\\txt.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                Solution savedObject = new Solution(44);
                oos.writeObject(savedObject);
                oos.flush();
                Solution loadedObject = new Solution(33);
                loadedObject = (Solution) ois.readObject();
            System.out.println(savedObject.string.equals(loadedObject.string));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
