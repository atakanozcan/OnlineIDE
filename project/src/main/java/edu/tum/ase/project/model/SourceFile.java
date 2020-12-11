package edu.tum.ase.project.model;

import javax.persistence.*;

@Entity
@Table(name = "project_source_file")
public class SourceFile {
    protected SourceFile(){}
    
    public SourceFile(Project project, String name) {
        key = new SourceFileKey(project, name);
    }
    
    @Id
    @GeneratedValue
    private String id;
    
    private SourceFileKey key;
    
    @Lob
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return key.getProject();
    }

    public void setProject(Project project) {
        key.setProject(project);
    }

    public String getName() {
        return key.getName();
    }

    public void setName(String name) {
        key.setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
