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

import lombok.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Task {
    //variable definitions
    @NonNull
    @Getter
    @Setter
    private String title;
    @Getter
    @NonNull
    @Setter
    private String description;
    @Getter
    @NonNull
    @Setter
    private Date deadline;
    @Getter
    @NonNull
    @Setter
    private Priority priority;
    @Getter
    @Setter
    private boolean taskCompleted;
    @Getter
    private ArrayList<String> labelList;
    @Getter
    private String taskProject;
    @Getter
    private Section section;
    @Getter
    private ArrayList<SubTask> subtaskList;

<<<<<<< HEAD
    public Task(String title, String description, Date deadline,String priority,boolean taskCompleted, ArrayList<String> labelist){
=======


    //public Task(String title, String description, Date deadline,Priority priority,boolean taskCompleted, ArrayList<String> labelist, ArrayList<SubTask> subtaskList){

    public Task(String title, String description, Date deadline,Priority priority){
    //initializer for Task
>>>>>>> release/v1.0.0
     this.title = title;
     this.description = description;
     this.deadline = deadline;
     this.priority = priority;
     this.taskCompleted = taskCompleted;
<<<<<<< HEAD
     this.lableList=labelist;

=======
     this.labelList=new ArrayList<>();
     this.subtaskList = new ArrayList<>();
     this.taskCompleted = false;

    }
    public void addLabel(String label){
        labelList.add(label);

    }
    public void removeLabel(String label){
        labelList.remove(label);
    }
    public void removeSubTask(SubTask subtask){
        subtaskList.remove(subtask);
    }

    public String toString(){
        String ret = title+"\n"+description+"\n"+deadline+"\n"+priority+"\nIs this complete? "+taskCompleted;
        ret+="\nSubtasks:"+subtaskList;
        ret+="\nLabels: "+labelList;
        return ret;
>>>>>>> release/v1.0.0
    }

}
