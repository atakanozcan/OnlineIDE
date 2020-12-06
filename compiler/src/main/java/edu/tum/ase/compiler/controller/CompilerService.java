package edu.tum.ase.compiler.controller;

import edu.tum.ase.compiler.model.SourceCode;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.Runtime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CompilerService {

    public CompilerService() {
    }

    public SourceCode compile(SourceCode sourceCode) throws IOException {

        Process process = null;
        BufferedReader reader = null;
        InputStream stream = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        String command = "";

        //Create the executable file from sourcecode object
        File executableFile = new File(sourceCode.getFileName());
        executableFile.createNewFile();

        //Delete file after JVM terminates
        executableFile.deleteOnExit();
        Path path = Paths.get(sourceCode.getFileName());
        Files.write(path, sourceCode.getCode().getBytes());

        //Specify the compiler
        if ( sourceCode.getFileName().split("\\.")[1].equals("java")){
            command = "javac";
        }
        else if ( sourceCode.getFileName().split("\\.")[1].equals("c")){
            command = "gcc";
        }

        //Compile the file
        try {
            process = Runtime.getRuntime().exec(command+" "+sourceCode.getFileName());
            reader = new BufferedReader( new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine())!= null)
            {
                buffer.append(line + "\n");
            }
            sourceCode.setStderr(buffer.toString());
            if (sourceCode.getStderr().equals("")){
                sourceCode.setCompilable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCode;
    }
}
