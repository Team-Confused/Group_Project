/**
 * MIT License
 *
 * Copyright (c) 2021 Team-Confused
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package Todo_Manager;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.ListView;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;




@Log
public class Manager {

    //variable definitions with relavent setters, getters, etc.
    @Getter
    private static ArrayList<User> users = new ArrayList<>();
    @Getter
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    @Getter
    private static ArrayList<String> labelList = new ArrayList<>();
    @Getter
    private static User loggedInUser;
    private static ArrayList<Section> sections = new ArrayList<>();
    @Getter
    private static ArrayList<SubTask> subtaskList = new ArrayList<>();


    //login method
    /*
        returns a boolean (true on login success, false on failure)
        parameters are the email and password (both of which are strings)
     */
    public static boolean login(String email, String password) throws IOException {
        //load the users
        loadUsers();
        User temp;  //create a temporary user

        //for each user in the set of users,
        for (User u : users) {
            //if the inputted email matches a user
            if (u.getEmail().equals(email)) {
                //and the unputted password matches the same user's.  At this point, user exists
                if (u.getPassword().equals(password)) {
                    //at this point, user exists and password is correct.
                    //set this person in the list of users to be "logged in"
                    loggedInUser = u;
                    log.info("logged-in: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

                    //load their user data
                    loadUserData();
                    log.info("loaded user data for: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

                    //return a success
                    return true;
                }
                //if the password is not correct,
                else {
                    //if password is incorrect, break from loop
                    break;
                }
            }
        }//if user does not exist or password is incorrect it will return false
        return false;
    }


    //logout
    /*
        logout the active user by setting the "loggedInUser" variable to null.
        Save the user data.
        Then, clear the tasks, labels, and sections
     */
    public static void logout() throws IOException {
        saveUsers();
        if (loggedInUser != null) {
            log.info("saving user " + getLoggedInUser().getFirstName() + " " + getLoggedInUser().getLastName() + "'s data due to logout");


            log.info("logged out user:" + getLoggedInUser().getFirstName() + " " + getLoggedInUser().getLastName());
        }
        loggedInUser = null;

        //empty the tasks, labels, and sections
        tasks = new ArrayList<>();
        labelList = new ArrayList<>();
        sections = new ArrayList<>();
    }


    //addUser
    /*
        add a user (typically from the register screen)
        returns a boolean (true is the new user has been added, false if the user email is already linked to a user)
     */
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


    //saveUsers
    /*
        save the list of users and some of their data to a JSon file
        there is no return
        there are no parameters
     */
    private static void saveUsers() throws IOException {
        //functionality for saving user list
        //loadUsers();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        System.out.println("users (from saveUser):" + users);
        String json = gson.toJson(users);
        try {
            //Files.put(Paths.get("./Users.json"), json);
            Files.writeString(Paths.get("./Users.json"), json);


        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }


    //loadUsers
    /*
        load the list of users and some of their data from a JSON file
        there is no return
        there are no parameters
     */
    public static void loadUsers() throws IOException {
        //loads user list
        Gson gson = new Gson();
        Type usersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        try {
            //populate the "users" ArrayList with the data from the JSON file
            users = gson.fromJson(Files.readString(Paths.get("./Users.json")), usersType);
            //System.out.println("users:"+users);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }


    //saveUserData
    /*
        saves the data of the currently-logged-in user to a JSON file.
        there are no returns
        there are no parameters
     */
    public static void saveUserData() throws IOException {
        //saves data of the logged in user
        Gson gson = new Gson();
        //open the user's dedicated file
        FileWriter file = new FileWriter("./UserFiles/" + loggedInUser.getId() + ".json", true);
        BufferedWriter blankWriter = new BufferedWriter(new FileWriter("./UserFiles/" + loggedInUser.getId() + ".json", false));
        //write to the file
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

    //loadUserData
    /*
        load the data for the currently logged in user from their dedicated JSON file
        there are no parameters
        there is no return
     */
    private static void loadUserData() throws IOException {
        Gson gson = new Gson();
        //checks if file exists
        File tempFile = new File("./UserFiles/" + loggedInUser.getId() + ".json");
        boolean exists = tempFile.exists();
        if (exists) {
            //if it exists, reads in each type
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


    public static void modifyTask(Task workingTask, String title, String description, Date deadline, Priority priority) throws IOException {
        workingTask.setTitle(title);
        workingTask.setDescription(description);
        workingTask.setDeadline(deadline);
        workingTask.setPriority(priority);
        saveUserData();
    }

    public static void removeTask(Task workingTask) throws IOException {
        tasks.remove(workingTask);
        saveUserData();
    }
    public static void removeSubTask(Task workingTask,SubTask subtask) throws IOException {
        workingTask.removeSubTask(subtask);
        saveUserData();
    }

    public static void addLabel(String label, Task workingTask) throws IOException {
        workingTask.addLabel(label);
        saveUserData();
    }
    public static void removeLabel(String label, Task workingTask) throws IOException {
        workingTask.removeLabel(label);
        saveUserData();
    }

    //addSubTask
    /*
        add a new sub-task to a task
        parameters: title of sub-task, description, deadline, priority, and boolean of completeness
     */
    public static void addSubTask(Task workingTask,String title, String description, Date deadline, Priority priority) throws IOException {
        SubTask subTask = new SubTask(title, description, deadline, priority);
        //add new subtask
      //  subtaskList.add(subTask);
        workingTask.addSubTask(subTask);
        saveUserData();
    }

    //addTask
    /*
        add a task
        the parameters are (String title, String description, Date deadline,String priority,boolean taskCompleted)
        there is no return
     */
    public static void addTask(String title, String description, Date deadline, Priority priority) throws IOException {
        //temporary task
        Task task = new Task(title, description, deadline, priority);
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        //add the task to the list of tasks "tasks"
        tasks.add(task);
        //save the user's data
        saveUserData();
    }


    //addSection
    /*
        add a new section
        the parameters are Title and Description (both are strings)
        there is no return
     */
     static void addSection(String title, String description) throws IOException {
        Section section = new Section(title, description);
        //add the new section to sections
        sections.add(section);
        saveUserData();
    }



    //searchTask
    /*
        searches through the tasks for an inputted parameter
        parameter: search parameter (String)
        returns and arrayList of all tasks containing the search parameter
     */
    static ArrayList<Task> searchTask(String object) throws IOException {
        ArrayList<Task> taskSearch = new ArrayList<Task>();
        //search thorough each element in the list of tasks for any matches to the parameter
        for (Task workingTask : Manager.getTasks()) {
            if(workingTask.getTitle().contains(object) || workingTask.getLabelList().contains(object)) {
                taskSearch.add(workingTask);
                }



        }
      System.out.println(taskSearch);
        return taskSearch;
    }


    //logoutUser
    /*
        Logout a user by their unique user id (UUID)
        There is no return
    */
//    public static void logoutUser(UUID id) throws IOException {
//        //write to log that no users are logged in
//        log.info("logged out user:" + id);
//
//        //set the "loggedInUser" variable to "null" to signify that there is no user logged in
//        loggedInUser = null;
//
//        //save the user's data
//        saveUserData();
//        log.info("saving user "+id+"'s data due to logout");
//    }


    //user password reset
    /*
        reset any user's password
        return 1 if success and -1 if failure
     */
    public static int adminPasswordReset(UUID id, String newPassword) throws IOException {
        log.info("user: " + loggedInUser.getId() + " password changed from:\"" + loggedInUser.getPassword() + "\" to: \"" + newPassword);

        //create list called "active" which takes the users list, filters it based on the logic (user.getId() == id)
        List<User> active = users.stream().filter(user -> user.getId() == id).collect(Collectors.toList());

        //perform password reset on first user in list (there should only be one user with the id anyways)
        if (!active.isEmpty()) {
            //set the user's password to the function-parameter password
            active.get(0).setPassword(newPassword);

            //save the updated user data
            //saveUserData();
            saveUsers();
            return 1;
        }

        //return error message if the list is empty
        else {
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
        log.info("adminPasswordReset called with \"" + newPassword + "\" being the new password.");
        return adminPasswordReset(loggedInUser.getId(), newPassword);
    }


    //main method (it is mainly used for testing of Manager methods)
    public static void main(String args[]) throws IOException {
        //tests for various methods
        // Manager.Sort();


        //generic user account
//        addUser("John",
//                "Doe",
//                "Pa55w0rd",
//                     "Only child of Jack and Jill Doe",
//                  "example@gmail.com",
//                        Path.of("/home/john/pictures/img.pdf"),
//                        false);
//
//        //login generic user
        // Manager.loadUsers();
        //System.out.println(users);
        // login("example@gmail.com","newPassword");
        //saveUserData();
        // addTask("sdfsdf","sdf sdf sd weg re grerge rgeerg.",new Date(444,4,4),Priority.Low,false);

//        //reset password of generic user
//        userPasswordReset("newP455W0rd");
//
//        loadUsers();
//        addTask("test","test",new Date(555,5,4),"test",false);
//        saveUsers();
//        System.out.println(users);
//
//

//        //loadUserData();
//        saveUserData();


    }


}



