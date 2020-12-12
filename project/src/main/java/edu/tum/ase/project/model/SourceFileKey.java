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
    
    // needed to use SourceFileKey as a Compound ID
    public boolean equals(SourceFileKey other) {
        return project.getId().equals(other.getProject().getId())
                && name.equals(other.getName());
    }

    // needed to use SourceFileKey as a Compound ID
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + project.getId().hashCode();
        hash = 31 * hash + name.hashCode();
        return hash;
    }
    
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
