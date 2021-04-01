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

    public Sort() {

        for (Task element : tasks) {
            if (task = true) {
                System.out.println(tasks);   // not to sure if this solves I tried using return tasks which resulted in an error
            }
        }

        for (Task element : tasks) {
            if (sortDate = true) {
                if (date == element.getDeadline()) {
                    System.out.println(element);
                }
            }
        }
        for (String element : labelList) {
            if (label = true) {
                System.out.println(labelList);
            }


        }
    }
}
