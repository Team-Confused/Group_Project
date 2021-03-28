package Todo_Manager;

import lombok.*;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Section {
    @NonNull
    @Getter
    private String title;
    @NonNull
    @Getter
    private String description;

}
