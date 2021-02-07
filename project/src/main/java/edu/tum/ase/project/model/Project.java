package edu.tum.ase.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(generator = "system-uuid") @GenericGenerator(name = "system-uuid", strategy = "uuid") @Column(name = "project_id")
    private String id;
    @Column(name = "name", nullable = false, unique = true) private String name;


    @ElementCollection
    @CollectionTable(name = "project_project_users", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "userIds")
    private Set<String> userIds = new HashSet<>();

    // ... additional members, often include @OneToMany mappings
    protected Project() {
    // no-args constructor required by JPA spec
    // this one is protected since it shouldn't be used directly
    }
    public Project(String name, String userId) {
        this.name = name;
        addUser(userId);
    }

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

    public Set<String> getUserIds(){ return userIds; }

    public void addUser(String userId){ this.userIds.add(userId); }

    public void deleteUser(String userId){ this.userIds.remove(userId); }

    @Override
    public String toString() {
        return String.format("Project[id=%s, name=%s]", id, name);
    }
}
