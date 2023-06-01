package com.epam.mjc.nio;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String name = "Def";    //  **  fields of Profile   **
        int age = 0;            //
        String email = "";      //
        long phone = 0L;        //
        try (BufferedReader bufferedReader= Files.newBufferedReader(file.toPath())){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(": ");
                if (parts.length != 2) throw new IOException("Bad format:" + line);
                switch (parts[0]) {
                    case "Name":
                        name = parts[1];
                        break;
                    case "Age":
                        age = Integer.parseInt(parts[1]);
                        break;
                    case "Email":
                        email = parts[1];
                        break;
                    case "Phone":
                        phone = Long.parseLong(parts[1]);
                        break;
                    default:
                        throw new IOException("Bad format:" + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Profile(name, age, email, phone);
    }
}
