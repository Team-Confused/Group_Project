package Todo_Manager;

import lombok.*;

import java.util.Date;
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SubTask {
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
}
