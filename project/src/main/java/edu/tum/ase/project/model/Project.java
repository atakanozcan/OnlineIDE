package edu.tum.ase.project.model;
import org.hibernate.annotations.GenericGenerator; import javax.persistence.*;


@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(generator = "system-uuid") @GenericGenerator(name = "system-uuid", strategy = "uuid") @Column(name = "project_id")
    private String id;
    @Column(name = "name", nullable = false, unique = true) private String name;
    // ... additional members, often include @OneToMany mappings
    protected Project() {
    // no-args constructor required by JPA spec
    // this one is protected since it shouldn't be used directly
    }
    public Project(String name) {
        this.name = name;
    }
    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return String.format("Project[id=%s, name=%s]", id, name);
    }
}
