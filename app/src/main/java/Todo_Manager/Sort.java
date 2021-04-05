package Todo_Manager;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Log
public class Sort {

    //arrayLists
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();

    //booleans
    boolean label;
    boolean task;
    boolean sortDate;
    boolean priority;

    Date date;
    String p;

    //date setter
    public void setDate(Date date) {
        this.date = date;
    }

    //enum for Priority
    public enum Priority {
        Low,Medium,High,ASAP;
    }

    //sort by the task name (alphabetical)
    void sortByTask(ArrayList tasks)
    {
        log.info("sort tasks by task");
        Collections.sort(tasks);
        log.info("sorted tasks:" +tasks);

    }

    public Sort(boolean isTask, boolean isLabel, boolean isDate, boolean isPriority) throws IOException {
        //list of tasks
        ArrayList tasks = Manager.getTasks();

        //if we are sorting by task
        if(isTask)sortByTask(tasks);


        /*
        for (Task element : tasks) {
            if (task == true) {
                System.out.println(tasks);
            }
        }

        for (Task element : tasks) {
            if (sortDate == true) {
                if (date.before(element.getDeadline())) {
                    System.out.println(element);
                }
            }
        }
        for (String element : labelList) {
            if (label == true) {
                System.out.println(labelList);
            }
        }
        for (Task element : tasks){
            if(priority == true){
                if(p == element.getPriority()){
                    System.out.print(tasks);
                }
            }
        }



         */
    }


}
