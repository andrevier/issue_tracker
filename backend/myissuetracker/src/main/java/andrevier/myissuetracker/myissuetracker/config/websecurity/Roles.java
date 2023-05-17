package andrevier.myissuetracker.myissuetracker.config.websecurity;


// Pre-define roles for the users. All the roles must be get from this class.
public class Roles {
    private static String roles[] = new String[]{"USER", "ADMIN"};
    
    public static String user() {
        return roles[0];
    }
    
    public static String admin() {
        return roles[1];
    }

}
