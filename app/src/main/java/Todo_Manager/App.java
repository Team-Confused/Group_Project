/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package Todo_Manager;



import lombok.extern.java.Log;

import java.util.UUID;
import java.nio.file.Paths;


@Log
public class App {
    public String getGreeting() {
        log.info("string log test");
        return "Hello World!";

    }

    public static void main(String[] args) {

        System.out.println(new App().getGreeting());
        User test = new User("kevin","bacon",UUID.randomUUID(),"bip","uwu@gmail.com", Paths.get("C/users"),false);
        System.out.println(test);
        System.out.println(UUID.randomUUID());
    }
}

