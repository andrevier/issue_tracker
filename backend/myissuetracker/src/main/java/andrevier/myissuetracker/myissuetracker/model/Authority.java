package andrevier.myissuetracker.myissuetracker.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

    @Id
    @SequenceGenerator(
        name = "authority_sequence", 
        sequenceName = "authority_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "authority_sequence"
    )
    @Column(name = "authority_id", updatable = false)
    private Long authorityId;

    // Role is project or issue.
    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(targetEntity = UserData.class)
    @JoinColumn(
        name="user_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "user_id_authority_FK"))
    private UserData userData;


    public Authority() {

    }

    public Authority(String role, UserData userData) {
        this.role = role;
        this.userData = userData;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthority(String role) {
        this.role = role;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((userData == null) ? 0 : userData.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Authority other = (Authority) obj;
        if (authorityId == null) {
            if (other.authorityId != null)
                return false;
        } else if (!authorityId.equals(other.authorityId))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (userData == null) {
            if (other.userData != null)
                return false;
        } else if (!userData.equals(other.userData))
            return false;
        return true;
    }
}
