package andrevier.myissuetracker.myissuetracker.dto;

public class UserRequest implements UserRequestDto{
    private Long userId;
    private String userName;
    private String password;
    private String email;

    
    public UserRequest() {
    }

    public UserRequest(
        String email,String password) {
        this.userId = null;
        this.userName = null;
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

    @Override
    public String getEmail() {
        return this.email;
    }
    
}
