package Todo_Manager;

import lombok.*;

import java.nio.file.Path;
import java.util.UUID;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)


public class User {

    @NonNull
    @Getter
    private String firstName;
    @NonNull
    @Getter
    private String lastName;
    @NonNull
    @Getter
    private UUID id;
    @NonNull
    @Getter
    private String bio;
    @NonNull
    @Getter
    private String email;
    @NonNull
    @Getter
    private Path photo;
    @Getter
    private boolean isAdmin;



}
