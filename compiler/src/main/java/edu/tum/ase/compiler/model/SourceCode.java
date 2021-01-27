package edu.tum.ase.compiler.model;

public class SourceCode {

    private String code;
    private String name;
    private String stdout = "";
    private String stderr = "";
    private boolean compilable = false;


    public SourceCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public boolean isCompilable() {
        return compilable;
    }

    public void setCompilable(boolean compilable) {
        this.compilable = compilable;
    }
}
