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
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
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


    //enum for Priority
    /*
    public enum tempPriority {
        Low,Medium,High,ASAP;
    }
    */

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
    static ArrayList sortByLabel(String label)
    {
        //tasks
        //System.out.println(Manager.getTasks());

        //create a temporary ArrayList
        ArrayList<Task> sortedTasks = new ArrayList<Task>(); // = Manager.getTasks();

        //there is a label provided to filter
        if(!label.isEmpty())
        {
            //sort the temporary ArrayList based on the number of terms in the label list
            //sortedTasks.sort((u1, u2) -> u1.getLabelList().size().compareTo(u2.getLabelList().size()) );
        }
        //there is no label provided
        else
        {
            //sort the temporary ArrayList based on the tasks containing the same label inputted
            for(Task task : Manager.getTasks())
            {
                if(task.getLabelList().contains(label))
                {
                    sortedTasks.add(task);
                }
            }
        }

        log.info("sorted tasks: (based on task [alphabetical])\n" + sortedTasks);

        return sortedTasks;
    }


    // sort by date
    //date setter
    @Setter
    Date date;
    static ArrayList sortByDate(LocalDate tasks)
    {
        ArrayList sortedTasks = new ArrayList();

        return sortedTasks;
    }
    
    //sort by priority
    static ArrayList sortByPriority(Priority tasks)
    {
        ArrayList sortedTasks = new ArrayList();
        
        return sortedTasks;
    }

    public static ArrayList sortBy(boolean isTask, boolean isLabel, boolean isDate, boolean isPriority, LocalDate date, String label, Priority priority) throws IOException {
        //list of tasks
        ArrayList tasks = Manager.getTasks();
        ArrayList sortedTasks = new ArrayList();

        //if we are sorting by task
        if(isTask) sortedTasks = sortByTask();
        else if(isLabel) sortedTasks = sortByLabel(label);
        else if(isDate) sortedTasks = sortByDate(date);
        else if(isPriority) sortedTasks = sortByPriority(priority);

        return sortedTasks;
    }


}
