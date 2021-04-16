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

import org.junit.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;


public class test {
    private static Date testDate;
    @BeforeClass
    public static void beforeClass() throws IOException {
        Manager.loadUsers();
        testDate = new Date();
    }

    @Before
    public void beforeEach() throws IOException {
        //assertTrue("Login test for existing user",Manager.login("example@gmail.com", "newPassword"));

    }
    @After
    public void afterEach() throws IOException {
        Manager.logout();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        Manager.logout();
    }

    @Test
    public void testAddUserThatAlreadyExists() throws IOException {
        Boolean expected = Manager.addUser("kevin", "bacon", "password", "sdfsadfsdf", "email@gmail.com", Paths.get("../ConfusedLogo.jpg"), false);
        assertFalse("Should fail because user already exists", expected);

    }

    @Test
    public void testLoginForUser() throws IOException {
        assertTrue("Should succeed because user exists", Manager.login("email@gmail.com", "password"));
    }

    @Test
    public void testAddTask() throws IOException {
        Manager.login("email@gmail.com", "password");
        Manager.addTask("test","test",testDate,Priority.High);
        Task expected = new Task("test","test",testDate,Priority.High);
        assertEquals("Created task\n"+expected+" should be equal to task created in manager\n"+Manager.getTasks().get(Manager.getTasks().size()-1),expected,Manager.getTasks().get(Manager.getTasks().size()-1));
        Manager.removeTask(expected);
    }

    @Test
    public void testTaskModification() throws IOException {
        Manager.login("email@gmail.com", "password");
        Manager.addTask("test","test",testDate,Priority.High);
        Manager.modifyTask(Manager.getTasks().get(Manager.getTasks().size()-1),"test but edited","test",testDate,Priority.High);
        Task expected = new Task("test but edited","test",testDate,Priority.High);
        assertEquals("edited task should be equal to created task",expected,Manager.getTasks().get(Manager.getTasks().size()-1));
    }


    @Test
    public void testRemoveTask() throws IOException {
        Manager.login("email@gmail.com", "password");
        Task task = new Task("test","test",testDate,Priority.High);
        Manager.addTask(task.getTitle(),task.getDescription(),task.getDeadline(),task.getPriority());
        Manager.removeTask(task);
        assertFalse("The task has been removed, so it should not exist in the list",Manager.getTasks().contains(task));
    }


    @Test
    public void testSort() throws IOException {
        //logout any current user
        Manager.logout();
        //login
        Manager.login("example@gmail.com", "newPassword");

        System.out.println("tasks of "+Manager.getLoggedInUser().getFirstName() + " " + Manager.getLoggedInUser().getLastName() + ": " + Manager.getTasks());
        //sort by task (alphabetical)
        //Sort.sortBy(true,false,false,false, null, null, null);

        //sort by label
        //String label = Manager.getLabelList().get(0);
        //Sort.sortBy(false,true,false,false, null, label, null);


        //sort by date

        //assertFalse("The task has been removed, so it should not exist in the list");

        //logout
        Manager.logout();

    }




}
