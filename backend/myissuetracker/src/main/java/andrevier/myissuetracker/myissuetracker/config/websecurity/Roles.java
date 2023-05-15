package andrevier.myissuetracker.myissuetracker.config.websecurity;

import java.util.Arrays;
import java.util.List;

// Pre-define roles for the users. All the roles must be get from this class.
public class Roles {
    private static List<String> roles = Arrays.asList("USER", "ADMIN");
    
    public static String user() {
        return roles.get(0);
    }
    
    public static String admin() {
        return roles.get(1);
    }

}
