package com.company.utils;

import java.io.*;

public class FileUtils {

    public static boolean isFileExists(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            fileOut.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static Object ReadObjectFromFile(String filepath)
    {
        Object objectToReturn = null;
        try {

            FileInputStream f = new FileInputStream(new File(filepath));
            ObjectInputStream o = new ObjectInputStream(f);

            objectToReturn = o.readObject();
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            return null;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return objectToReturn;
    }

}
