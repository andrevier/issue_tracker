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

    public static String projectAdmin(Long projectId) {
        // Project admin authority. Allows CRUD operations in the project and
        // its issues.
        return "PROJECT_ADMIN:" + projectId.toString();
    }

    public static String project(Long projectId) {
        // Allows work in the project authority. Allows to:
        // - read the content of the project;
        // - read the content of all issues in the project;
        // - create an issue;
        // - update and delete own issues.
        return "PROJECT:" + projectId.toString();
    }

    public static String issueAdmin(Long issueId) {
        // Allows to CRUD the issue with id specified.
        // Works together with the project authority.
        return "ISSUE_ADMIN:" + issueId.toString();
    }

}
