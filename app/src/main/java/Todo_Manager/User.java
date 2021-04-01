package Todo_Manager;

import lombok.*;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.UUID;

@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)


public class User{

    @NonNull
    @Getter
    private String firstName;


    @NonNull
    @Getter
    public String lastName;
    @NonNull
    @Getter
    @Setter
    public String password;
    @NonNull
    @Getter
    public UUID id;
    @NonNull
    @Getter
    public String bio;
    @NonNull
    @Getter
    public String email;
    @NonNull
    @Getter
    public String photo;
    @Getter
    public boolean isAdmin;



}
