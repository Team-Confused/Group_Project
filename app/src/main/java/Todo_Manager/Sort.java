package Todo_Manager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Sort {

    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();
    boolean label;
    boolean task;
    boolean sortDate;
    boolean priority;
    Date date;
    String p;

    public void setDate(Date date) {
        this.date = date;
    }
    public enum Priority {
        Low,Medium,High,ASAP;
    }

    public Sort(boolean isTask, boolean isLabel, boolean isDate, boolean isPriority) throws IOException {


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


    }
}
