package com.akn.projectcomp202;

public class Username
{
    private static String username = "default";

    public static String getUsername(){
        return Username.username;
    }

    public static void setUsername(String var){
        Username.username = var;
    }
}