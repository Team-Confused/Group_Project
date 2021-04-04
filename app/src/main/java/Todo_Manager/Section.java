package Todo_Manager;

import lombok.*;

//project lombok operators
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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


}
