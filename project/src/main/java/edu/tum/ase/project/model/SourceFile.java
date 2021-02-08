package edu.tum.ase.project.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "project_source_file")
public class SourceFile {
    protected SourceFile(){}

    public SourceFile(Project project, String name, String code) {
        this.project = project;
        this.name = name;
        this.code = code;
    }
    public SourceFile(Project project, String name) {
        this.project = project;
        this.name = name;
    }

    // A SourceFile is uniquely identified by its name (path) and the project it belongs to.
    @Id
    @GeneratedValue(generator = "system-uuid") @GenericGenerator(name = "system-uuid", strategy = "uuid") @Column(name = "sourcefile_id")
    private String id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String code;

    @ManyToOne
    private Project project;

    private String name;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
