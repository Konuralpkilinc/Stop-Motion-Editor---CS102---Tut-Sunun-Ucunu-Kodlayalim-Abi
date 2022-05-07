package stopmotioneditor;

/**
 *
 * @author Emir
 */
import java.io.File;
import java.io.IOException;

import java.io.IOException;

import java.io.File;
import java.util.ArrayList;

public class DatabaseTester {
    public static void main(String[] args) {
        //Image image = new Image("Images/Test.jpg");
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(5);
        al.add(6);

        Database.saveImageToDatabase("aUser", "project1", 1);
        ArrayList<Integer> ret = (ArrayList<Integer>) Database.deserialize();
        System.out.println(ret);

        
    }
}