package Todo_Manager;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.extern.java.Log;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Log
public class Manager {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<String> labelList = new ArrayList<>();
    private static User loggedInUser;//todo: this should be set to user on login and null on logout and/or startup
    private static ArrayList<Section> sections = new ArrayList<>();


    public static boolean login(String email, String password) throws IOException {
        User temp;
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                //at this point, user exists
                if (u.getPassword().equals(password)) {
                    //at this point, user exists and password is correct.
                    loggedInUser = u;
                    loadUserData();
                    return true;
                } else {
                    //if password is incorrect, break from loop
                    break;
                }
            }
        }//if user does not exist or password is incorrect it will return false
        return false;
    }

    public static boolean addUser(String firstName, String lastName, String password, String bio, String
            email, Path picture, Boolean isAdmin) throws IOException {
        //ui should call this and return to login screen
        //checks that email has not been used before
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        //this creates user and converts the path to a string, users can't hold it as a path cause gson doesn't work with those.
        User temp = new User(firstName, lastName, password, UUID.randomUUID(), bio, email, picture.toString(), isAdmin);
        users.add(temp);
        log.info("New user " + temp + " has been created.");
        saveUsers();
        return true;
    }

    private static void saveUsers() throws IOException {
        //functionality for saving user list
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(users);
        try {
            Files.writeString(Paths.get("./Users.json"), json);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadUsers() throws IOException {
        //loads user list
        Gson gson = new Gson();
        Type usersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        try {
            users = gson.fromJson(Files.readString(Paths.get("./Users.json")), usersType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void saveUserData() throws IOException {
        //saves data of the logged in user
        Gson gson = new Gson();

        FileWriter file = new FileWriter("./UserFiles/" + loggedInUser.getId() + ".json", true);
        loggedInUser = users.get(0);//todo remove after implementing login
        BufferedWriter blankWriter = new BufferedWriter(new FileWriter("./UserFiles/" + loggedInUser.getId() + ".json", false));
        blankWriter.write("");
        blankWriter.close();
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(gson.toJson(tasks));
        writer.newLine();
        writer.write(gson.toJson(sections));
        writer.newLine();
        writer.write(gson.toJson(labelList));
        writer.close();

    }

    private static void loadUserData() throws IOException {
        Gson gson = new Gson();
        File tempFile = new File("./UserFiles/" + loggedInUser.getId() + ".json");
        boolean exists = tempFile.exists();
        if (exists) {
            loggedInUser = users.get(0);//todo remove after implementing login
            BufferedReader test = new BufferedReader(new FileReader("./UserFiles/" + loggedInUser.getId() + ".json"));
            Type tasksToken = new TypeToken<ArrayList<Task>>() {
            }.getType();
            tasks = gson.fromJson(test.readLine(), tasksToken);
            Type sectionsToken = new TypeToken<ArrayList<Section>>() {
            }.getType();
            sections = gson.fromJson(test.readLine(), sectionsToken);
            Type labelsToken = new TypeToken<ArrayList<String>>() {
            }.getType();
            labelList = gson.fromJson(test.readLine(), labelsToken);
            test.close();
        }
    }
    private void addTask( String title, String description, Date deadline,String priority,boolean taskCompleted){
        Task task = new Task( title, description, deadline,priority,taskCompleted, labelList);
        tasks.add(task);

    }

    private static String Search(String object){
        for (String element : labelList){
            if(element.contains(object)){
                return element;
            }
            else{
                System.out.println("Search not found");
            }
        }
        for(Task element : tasks){
            if(element.getTitle() == object){
                return String.valueOf(element);

            }
            else{
                System.out.println("Search not found");
            }
        }



        return object;
    }


    public static void main(String args[]) throws IOException {
        //tests for various methods

        loadUsers();
        saveUsers();
        System.out.println(users);


        //loadUserData();
        //saveUserData();

    }


}
