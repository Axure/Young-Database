package com.zjuqsc.database;

import java.util.Scanner;

/**
 * Created by zhenghu on 2016-05-27.
 */
public class main {


    public static void main(String args[]) {
        System.out.print("Haha");


        boolean toQuit = false;
        Scanner in = new Scanner(System.in);
        while (!toQuit) {
            String input = in.nextLine();
            System.out.println(input);
            if (input.equals("quit")) {
                toQuit = true;
            }
        }
    }
}
