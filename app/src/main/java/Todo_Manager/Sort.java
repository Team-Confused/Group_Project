<<<<<<< HEAD
package Todo_Manager;

import java.util.ArrayList;
import java.util.Date;

public class Sort {
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();
    boolean label;
    boolean task;
    boolean subTask;
    boolean sortDate;
    Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Sort() {  //

        for (Task element : tasks) {
            if (task = true) {
                System.out.println(tasks);   // not to sure if this solves I tried using return tasks which resulted in an error
=======
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
import lombok.extern.java.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Log
public class Sort {

    //arrayLists
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Task> sortedTasks = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();

    //booleans
    boolean label;
    boolean task;
    boolean sortDate;
    boolean priority;

    Date date;
    String p;

    //date setter
    public static void setDate(LocalDate date) {
        date = date;
    }

    //enum for Priority
    public enum Priority {
        Low,Medium,High,ASAP;
    }

    //sort by the task name (alphabetical)
    static void sortByTask(ArrayList tasks)
    {
        log.info("sort tasks by task");
        ArrayList simpleTasks = new ArrayList();
        for(int i =0; i<tasks.size(); i++)
         simpleTasks.add(tasks.get(i).toString());
        simpleTasks.sort((Comparator) tasks);

        log.info("sorted tasks:" + sortedTasks);

        tasks = sortedTasks;
    }

    static void sortbyLabel(ArrayList tasks)
    {
        log.info("sort by label");
    }

    static void sortbyDate(ArrayList tasks)
    {

    }

    public static void sortBy(boolean isTask, boolean isLabel, boolean isDate, boolean isPriority) throws IOException {
        //list of tasks
        ArrayList tasks = Manager.getTasks();

        //if we are sorting by task
        if(isTask)sortByTask(tasks);
        else if(isLabel)sortbyLabel(tasks);
        else if(isDate)sortbyDate(tasks);


        /*
        for (Task element : tasks) {
            if (task == true) {
                System.out.println(tasks);
>>>>>>> release/v1.0.0
            }
        }

        for (Task element : tasks) {
<<<<<<< HEAD
            if (sortDate = true) {
                if (date == element.getDeadline()) {
=======
            if (sortDate == true) {
                if (date.before(element.getDeadline())) {
>>>>>>> release/v1.0.0
                    System.out.println(element);
                }
            }
        }
        for (String element : labelList) {
<<<<<<< HEAD
            if (label = true) {
                System.out.println(labelList);
            }


        }
    }
=======
            if (label == true) {
                System.out.println(labelList);
            }
        }
        for (Task element : tasks){
            if(priority == true){
                if(p.equals(element.getPriority())){
                    System.out.print(tasks);
                }
            }
        }



         */
    }


>>>>>>> release/v1.0.0
}
