package edu.tum.ase.project.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class SourceFileKey implements Serializable {
    @ManyToOne
    private Project project;
    private String name;
    
    protected SourceFileKey() {}
    
    public SourceFileKey(Project project, String name){
        this.project = project;
        this.name = name;
    }
    
    // TODO: Implement equals() and hashCode()
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
