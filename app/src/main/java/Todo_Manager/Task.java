package Todo_Manager;

import lombok.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@ToString
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
    private String priority;
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

}
