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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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
            Type tasksToken = new TypeToken<ArrayList<Task>>(){
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

    private void addSection(String title, String description){
        Section section = new Section(title, description);
        sections.add(section);
    }

    private static ArrayList<String> Search(String object){
        ArrayList<String> labelSearch = new ArrayList<>();
        for (String element : labelList){
            if(element.contains(object)){
                labelSearch.add(element);
            }
            else{
                System.out.println("Search not found");
            }
        }
        return labelSearch;
    }
    private static ArrayList<Task> searchTask(Task object){
        ArrayList<Task> taskSearch = new ArrayList<>();
        for(Task element : tasks){
            if(element == object){
                taskSearch.add(element);
            }
            else{
                System.out.println("Search not found");
            }
        }
        return taskSearch;
    }



    //logoutUser
    /*
        Logout a user by their unique user id (UUID)
        There is no return
    */
    public static void logoutUser(UUID id) throws IOException {
        //write to log that no users are logged in
        log.info("logged out user:" + id);

        //set the "loggedInUser" variable to "null" to signify that there is no user logged in
        loggedInUser = null;

        //save the user's data
        saveUserData();
        log.info("saving user "+id+"'s data due to logout");
    }



    //user password reset
    /*
        reset any user's password
        return 1 if success and -1 if failure
     */
    public static int adminPasswordReset(UUID id, String newPassword) throws IOException {
        log.info("user: "+loggedInUser.getId()+" password changed from:\""+loggedInUser.getPassword()+"\" to: \""+newPassword);

        //method 1 [works, but is a bit slower]:
        /*
        for (User person : users)
        {
            if(person.getId() == id)
            {
                System.out.println("user detected!");
                person.setPassword(newPassword);
            }
            System.out.println("new password:" + person.getPassword());
        }
        */

        //method 2 [also works, but is a bit faster]
        //create list called "active" which takes the users list, filters it based on the logic (user.getId() == id)
        List<User> active = users.stream().filter(user -> user.getId()==id).collect(Collectors.toList());

        //perform password reset on first user in list (there should only be one user with the id anyways)
        if(!active.isEmpty())
        {
            //set the user's password to the function-parameter password
            active.get(0).setPassword(newPassword);

            //save the updated user data
            saveUserData();
            return 1;
        }

        //return error message if the list is empty
        else
        {
            log.info("Error: The list of users is empty");
            return -1;
        }



    }



    //admin password reset
    /*
        reset the user's password
        returns whatever "adminPasswordReset" returns with the ID of the currently logged-in user as its UUID id parameter
     */
    public static int userPasswordReset(String newPassword) throws IOException {
        //call adminPasswordReset with the parameters of the current user and the new password
        return adminPasswordReset(loggedInUser.getId(), newPassword);
    }


    public static void main(String args[]) throws IOException {
        //tests for various methods

        //generic user account
        addUser("John",
                "Doe",
                "Pa55w0rd",
                     "Only child of Jack and Jill Doe",
                  "example@gmail.com",
                        Path.of("/home/john/pictures/img.pdf"),
                        false);

        //login generic user
        login("example@gmail.com","Pa55w0rd");

        //reset password of generic user
        userPasswordReset("newP455W0rd");

        //loadUsers();
        saveUsers();
        System.out.println(users);



        //loadUserData();
        //saveUserData();

    }


}
