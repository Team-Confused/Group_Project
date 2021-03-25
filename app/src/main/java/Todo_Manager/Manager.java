package Todo_Manager;

import lombok.extern.java.Log;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

@Log
public class Manager {

    public static ArrayList<User> users;

    public static void addUser(){
        //ui should call this and return to login screen
        //todo take in data from UI, will use temp placeholders until then
        User temp = new User("kevin","bacon", UUID.randomUUID(),"bip","uwu@gmail.com", Paths.get("C/users"),false);
        users.add(temp);
        log.info("New user "+temp+" has been created.");
    }


}
