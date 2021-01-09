package edu.tum.ase.compiler.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tum.ase.compiler.model.SourceCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompilerE2ERestTests {
    private final String URL = "/compile/";
    private static final String javaFileName = "App.java";
    private static final String cFileName = "App.c";

    @Autowired
    private MockMvc systemUnderTest;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnCompilableTrueWithNoErrors_When_postWithRightJavaCode() throws Exception {
        //given
        SourceCode sourceCode = new SourceCode();
        String rightJavaCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\");}}";
        sourceCode.setCode(rightJavaCode);
        sourceCode.setFileName(javaFileName);

        //when
        ResultActions result = systemUnderTest.perform(post(URL)
                .content(objectMapper.writeValueAsString(sourceCode))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(sourceCode.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value(sourceCode.getFileName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compilable").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stderr").isEmpty());
    }

    @Test
    public void shouldReturnCompilableFalseWithErrors_When_postWithWrongJavaCode() throws Exception {
        //given
        SourceCode sourceCode = new SourceCode();
        String wrongJavaCode = "public class App{public static void main(String[] args) {System.out.println(\"Hello World!\")}}";
        sourceCode.setCode(wrongJavaCode);
        sourceCode.setFileName(javaFileName);

        //when
        ResultActions result = systemUnderTest.perform(post(URL)
                .content(objectMapper.writeValueAsString(sourceCode))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(sourceCode.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value(sourceCode.getFileName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compilable").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stderr").isNotEmpty());
    }

    @Test
    public void shouldReturnCompilableTrueWithNoErrors_When_postWithRightCCode() throws Exception {
        //given
        SourceCode sourceCode = new SourceCode();
        String rightCCode = "#include<stdio.h>\n" + "int main(){printf (\"Hi World\\n\");return 0;}";
        sourceCode.setCode(rightCCode);
        sourceCode.setFileName(cFileName);

        //when
        ResultActions result = systemUnderTest.perform(post(URL)
                .content(objectMapper.writeValueAsString(sourceCode))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(sourceCode.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value(sourceCode.getFileName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compilable").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stderr").isEmpty());
    }

    @Test
    public void shouldReturnCompilableFalseWithErrors_When_postWithWrongCCode() throws Exception {
        //given
        SourceCode sourceCode = new SourceCode();
        String wrongCCode = "#include<stdio.h>\n" + "int main(){printf (\"Hi World\\n\");return 0}";
        sourceCode.setCode(wrongCCode);
        sourceCode.setFileName(cFileName);

        //when
        ResultActions result = systemUnderTest.perform(post(URL)
                .content(objectMapper.writeValueAsString(sourceCode))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(sourceCode.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileName").value(sourceCode.getFileName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compilable").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stderr").isNotEmpty());
    }

}
