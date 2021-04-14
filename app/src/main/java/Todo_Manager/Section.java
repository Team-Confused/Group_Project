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

import java.util.ArrayList;

//project lombok operators

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access =  AccessLevel.PUBLIC)
public class Section {
    //ensure that it is not null
    @NonNull
    //getter
    @Getter
    //declare the string "title" for the section
    private String title;

    //ensure that it is not null
    @NonNull
    //getter
    @Getter
    //declare the string "description" for the description
    private String description;
    @NonNull
    @Getter
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task){
        taskList.add(task);
    }
    public void removeTask(Task task){
        taskList.remove(task);
    }

    @Override
    public String toString(){
        return title+"\n"+description;
    }


}
