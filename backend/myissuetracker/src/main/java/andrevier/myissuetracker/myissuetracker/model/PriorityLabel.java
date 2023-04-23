package andrevier.myissuetracker.myissuetracker.model;

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

@Entity
@Table(name = "priority_label")
public class PriorityLabel {
    
    @Id
    @SequenceGenerator(
        name = "priority_label_id_seq", 
        sequenceName = "priority_label_id_seq", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "priority_label_id_seq"
    )
    @Column(name = "priority_label_id", updatable = false)
    private Long priorityId;

    @Column(name = "priority_name", nullable = false)
    private String priorityName;

    @OneToMany(orphanRemoval = true, mappedBy="priorityLabel")
    @JsonManagedReference
    private List<Issue> issueList;

    public PriorityLabel(String priorityName) {
        this.priorityName = priorityName;
    }
    
    public PriorityLabel() {
        this.priorityName = "example";
    }
    
    public Long getPriorityId() {
        return this.priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }
    
    public String getPriorityName() {
        return this.priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PriorityLabel))
            return false;
        PriorityLabel other = (PriorityLabel) o;
        return (this.priorityId == other.priorityId)
            && (this.priorityName.equals(other.priorityName));
    }

    @Override
    public int hashCode() {
        return this.priorityId.hashCode() * this.priorityName.hashCode();
    }
    
    @Override
    public String toString() {
        return "Priority { prirityId=" + this.priorityId 
            + ", priorityName=" + this.priorityName + "}";
    }
}
