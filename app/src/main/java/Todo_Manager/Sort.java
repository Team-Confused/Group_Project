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
import javafx.scene.control.Label;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/*
sort by:
 labels (user created tags associated with items) (alphabetical)
 priority
 due date
 alphabetical -- optional
 */

@Log
public class Sort {
    //arrayLists
    static ArrayList<Task> tasks,sortedTasks = new ArrayList<>();

    //booleans
    boolean label, task, sortDate, priority;


    //sort by the task name (alphabetical)
    static ArrayList sortByTask()
    {
        //create a temporary ArrayList
        ArrayList<Task> sortedTasks = Manager.getTasks();

        //sort the temporary ArrayList based on the task title
        sortedTasks.sort((u1, u2) -> u1.getTitle().compareTo(u2.getTitle()) );
        log.info("sorted tasks: (based on task [alphabetical])\n" + sortedTasks);
        return sortedTasks;
    }

    //sort by label
    static ArrayList sortByLabel(Object label)
    {
        //create a temporary ArrayList
        ArrayList<Task> sortedTasks = new ArrayList<Task>();

        //sort the temporary ArrayList based on the tasks containing the same label inputted
        for(Task task : Manager.getTasks())
        {
            //if the task's label is the same as the label parameter
            if(task.getLabel().equals(label))
            {
                //add the task in Tasks to the list
                sortedTasks.add(task);
            }
        }
        log.info("sorted tasks: (based on label)\n" + sortedTasks);

        return sortedTasks;
    }


    // sort by date
    //date setter
    @Setter
    Date date;
    static ArrayList sortByDate()
    {
        //create a temporary ArrayList
        ArrayList<Task> sortedTasks = Manager.getTasks();


        //sort the temporary ArrayList based on the task duedate
        sortedTasks.sort((u1, u2) -> u1.getDeadline().compareTo(u2.getDeadline()));
        log.info("sorted tasks: (based on date)\n" + sortedTasks);
        return sortedTasks;

    }
    
    //sort by priority
    static ArrayList sortByPriority(Priority priority)
    {
        ArrayList sortedTasks = new ArrayList();
        for(Task task : Manager.getTasks())
        {
            if(task.getPriority().equals(priority))
            {
                sortedTasks.add(task);
            }
        }
        return sortedTasks;
    }

    public static ArrayList sortBy(boolean isTask, boolean isLabel, Object label, boolean isDate, boolean isPriority, Priority priority) throws IOException {
        //list of tasks
        ArrayList tasks = Manager.getTasks();
        ArrayList sortedTasks = new ArrayList();

        log.info("isTask: " + isTask + "\ttaskSortType: "   +
                            "\nisLAbel: " + isLabel + "\tlabel: " +label +
                            "\nisDate" + isDate + "\tdate: "  +
                            "\nisPriority: " + isPriority + "\tpriority: " + priority);
        //if we are sorting by task
        if(isTask) sortedTasks = sortByTask();
        else if(isLabel) sortedTasks = sortByLabel(label);
        else if(isDate) sortedTasks = sortByDate();
        else if(isPriority) sortedTasks = sortByPriority(priority);
        else sortedTasks = Manager.getTasks();

        return sortedTasks;
    }


}
