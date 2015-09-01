package example.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.DispatcherServlet;

import javax.inject.Inject;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class RequestHeaderControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void string() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/string")
                        .header("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-header/string"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-header/string"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(ServletRequestBindingException.class)))
                .andReturn();

    }


    @Test
    public void stringWithName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithName")
                        .header("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-header/stringWithName"))
                .andReturn();

    }

    @Test
    public void stringWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("request-header/stringWithRequired"))
                .andReturn();

    }

    @Test
    public void stringWithOptional() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithOptional")
                        .header("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-header/stringWithOptional"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithOptional"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("request-header/stringWithOptional"))
                .andReturn();

        UUID.randomUUID().toString();

    }

    @Test
    public void stringWithOptionalAndDefaultValue() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithOptionalAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-header/stringWithOptionalAndDefaultValue"))
                .andReturn();
    }

    @Test
    public void stringWithDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-header/stringWithDefaultValue"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithDefaultValue")
                        .param("word", ""))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-header/stringWithDefaultValue"))
                .andReturn();


    }

    @Test
    public void stringWithRequiredAndDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/stringWithRequiredAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-header/stringWithRequiredAndDefaultValue"))
                .andReturn();

    }

    @Test
    public void integer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/integer")
                        .header("id", Integer.toString(Integer.MAX_VALUE)))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("id", is(Integer.MAX_VALUE)))
                .andExpect(
                        view().name("request-header/integer"))
                .andReturn();
        mvcResult = this.mockMvc.perform(
                get("/request-header/integer")
                        .header("id", "aaa"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(MethodArgumentTypeMismatchException.class)))
                .andReturn();

    }

    @Test
    public void allHeaders() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-header/allHeaders")
                        .header("word", "test")
                        .header("ids[1]", "111")
                        .header("ids[0]", "000")
        )
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("headers", is("{word=[test], ids[1]=[111], ids[0]=[000]}")))
                .andExpect(
                        view().name("request-header/allHeaders"))
                .andReturn();
    }

}
