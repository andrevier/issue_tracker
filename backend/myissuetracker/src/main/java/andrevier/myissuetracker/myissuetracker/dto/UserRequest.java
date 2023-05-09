package andrevier.myissuetracker.myissuetracker.dto;

public class UserRequest implements UserRequestDto{
    private Long userId;
    private String userName;
    private String password;
    private String email;
    
    public UserRequest() {
    }

    public UserRequest(
        Long userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
