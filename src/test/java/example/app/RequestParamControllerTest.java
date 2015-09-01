package example.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.DispatcherServlet;

import javax.inject.Inject;
import java.util.Enumeration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class RequestParamControllerTest {


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
                get("/request-param/string")
                        .param("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-param/string"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-param/string"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(MissingServletRequestParameterException.class)))
                .andReturn();

    }


    @Test
    public void stringWithName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithName")
                        .param("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-param/stringWithName"))
                .andReturn();

    }

    @Test
    public void stringWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("request-param/stringWithRequired"))
                .andReturn();

    }


    @Test
    public void stringWithOptional() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithOptional")
                        .param("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("request-param/stringWithOptional"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithOptional"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("request-param/stringWithOptional"))
                .andReturn();

    }

    @Test
    public void stringWithOptionalAndDefaultValue() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithOptionalAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-param/stringWithOptionalAndDefaultValue"))
                .andReturn();
    }

    @Test
    public void stringWithDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-param/stringWithDefaultValue"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithDefaultValue")
                        .param("word", ""))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-param/stringWithDefaultValue"))
                .andReturn();


    }

    @Test
    public void stringWithRequiredAndDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/stringWithRequiredAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("request-param/stringWithRequiredAndDefaultValue"))
                .andReturn();

    }

    @Test
    public void integer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/integer")
                        .param("id", Integer.toString(Integer.MAX_VALUE)))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("id", is(Integer.MAX_VALUE)))
                .andExpect(
                        view().name("request-param/integer"))
                .andReturn();
        mvcResult = this.mockMvc.perform(
                get("/request-param/integer")
                        .param("id", "aaa"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(MethodArgumentTypeMismatchException.class)))
                .andReturn();
    }

    @Test
    public void collection() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/collection")
                        .param("words", "word1")
                        .param("words", "word2"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("words", is("[word1, word2]")))
                .andExpect(
                        view().name("request-param/collection"))
                .andReturn();

    }

    @Test
    public void collectionWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/collectionWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("words", is("null")))
                .andExpect(
                        view().name("request-param/collectionWithRequired"))
                .andReturn();
    }

    @Test
    public void collectionWithDefault() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/collectionWithDefault"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("words", is("[1, 2, 3]")))
                .andExpect(
                        view().name("request-param/collectionWithDefault"))
                .andReturn();
    }

    @Test
    public void collectionWithOptional() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/collectionWithOptional")
                        .param("words", "test1")
                        .param("words", "test2")
                        .param("words", "test3"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("words", is("[test1, test2, test3]")))
                .andExpect(
                        view().name("request-param/collectionWithOptional"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/request-param/collectionWithOptional"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("words", is("[a, b, c]")))
                .andExpect(
                        view().name("request-param/collectionWithOptional"))
                .andReturn();
    }


    @Test
    public void array() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/array")
                        .param("ids", "1")
                        .param("ids", "10"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("ids", is("[1, 10]")))
                .andExpect(
                        view().name("request-param/array"))
                .andReturn();
    }

    @Test
    public void arrayWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/arrayWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("ids", is("null")))
                .andExpect(
                        view().name("request-param/arrayWithRequired"))
                .andReturn();
    }

    @Test
    public void allParams() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-param/allParams")
                        .param("words", "test1")
                        .param("words", "test2")
                        .param("words", "test3")
                        .param("ids[1]", "111")
                        .param("ids[0]", "000")
        )
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("params", is("{words=[test1, test2, test3], ids[1]=[111], ids[0]=[000]}")))
                .andExpect(
                        view().name("request-param/allParams"))
                .andReturn();
    }


}
