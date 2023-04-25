package andrevier.myissuetracker.myissuetracker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "User")
@Table(name = "user_data", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "user_email")
})
public class User {

    @Id
    @SequenceGenerator(
        name = "user_sequence", 
        sequenceName = "user_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "user_sequence"
    )
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false)
    private String email;

    @OneToMany(orphanRemoval = true, mappedBy="user")
    @JsonManagedReference
    private List<ManageProject> manageProjectList;

    @OneToMany(orphanRemoval = true, mappedBy="user")
    @JsonManagedReference
    private List<ManageIssue> manageIssueList;

    public User() {
       this.userName = "";
       this.email = "";
       this.password = "";
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmpty() {
        if (this.email == "" && this.password == "" && this.userName == "") {
            return true;
        }
        return false;
    }

    public void removeManageProject(ManageProject manageProject) {
        this.manageProjectList.remove(manageProject);                
    }

    public List<ManageProject> getManageProjectList() {
        return manageProjectList;
    }

    public void setManageProjectList(List<ManageProject> manageProjectList) {
        this.manageProjectList = manageProjectList;
    }

        
    public List<ManageIssue> getManageIssueList() {
        return manageIssueList;
    }

    public void setManageIssueList(List<ManageIssue> manageIssueList) {
        this.manageIssueList = manageIssueList;
    }

    public void removeManageIssue(ManageIssue manageIssue) {
        this.manageIssueList.remove(manageIssue);                
    }

    @Override
    public String toString() {
        return "User [userId=" + this.userId + ", userName=" + this.userName + ", password=" + this.password
                + ", email=" + this.email
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        return (other.getUserId() != null) && (other.getEmail() == this.email);
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode() * this.email.hashCode();
    }
}
