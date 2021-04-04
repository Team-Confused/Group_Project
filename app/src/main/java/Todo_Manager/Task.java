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

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Task {
    @NonNull
    @Getter
    private String title;
    @Getter
    @NonNull
    private String description;
    @Getter
    @NonNull
    private Date deadline;
    @Getter
    @NonNull
    private Priority priority;
    @Getter
    @Setter
    private boolean taskCompleted;
    @Getter
    @NonNull
    private ArrayList<String> lableList;
    @Getter
    @NonNull
    private String taskProject;
    @Getter
    @NonNull
    private Section section;
    @Getter
    @NonNull
    private ArrayList<SubTask> subtaskList;

<<<<<<< Updated upstream
    public Task(String title, String description, Date deadline,String priority,boolean taskCompleted, ArrayList<String> labelist, ArrayList<SubTask> subtaskList){
=======
    public Task(String title, String description, Date deadline,Priority priority){
>>>>>>> Stashed changes
     this.title = title;
     this.description = description;
     this.deadline = deadline;
     this.priority = priority;
<<<<<<< Updated upstream
     this.taskCompleted = taskCompleted;
     this.lableList=labelist;
     this.subtaskList = subtaskList;
=======
     this.taskCompleted = false;
>>>>>>> Stashed changes

    }

    public String toString(){
        String ret = title+"\n"+description+"\n"+priority+"\nIs this complete? "+taskCompleted;

        return ret;
    }

}
