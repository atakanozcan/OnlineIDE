package edu.tum.ase.compiler.controller;

import edu.tum.ase.compiler.model.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class CompilerController {
    @Autowired
    private CompilerService compilerService;

    @RequestMapping(path = "/compile", method = RequestMethod.POST)
    public SourceCode compile(@RequestBody SourceCode sourceCode) throws IOException {
        return compilerService.compile(sourceCode);
    }
}
