package example.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import javax.servlet.http.Cookie;

import java.util.Enumeration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class CookieValueControllerTest {


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
                get("/cookie-value/string")
                        .cookie(new Cookie("word", "aaaa")))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("cookie-value/string"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/cookie-value/string"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(ServletRequestBindingException.class)))
                .andReturn();

    }


    @Test
    public void stringWithName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithName")
                        .cookie(new Cookie("word", "aaaa")))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("cookie-value/stringWithName"))
                .andReturn();

    }

    @Test
    public void stringWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("cookie-value/stringWithRequired"))
                .andReturn();

    }

    @Test
    public void stringWithOptional() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithOptional")
                        .cookie(new Cookie("word", "aaaa")))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("cookie-value/stringWithOptional"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithOptional"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("cookie-value/stringWithOptional"))
                .andReturn();

    }

    @Test
    public void stringWithOptionalAndDefaultValue() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithOptionalAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("cookie-value/stringWithOptionalAndDefaultValue"))
                .andReturn();
    }

    @Test
    public void stringWithDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("cookie-value/stringWithDefaultValue"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithDefaultValue")
                        .cookie(new Cookie("word", "")))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("cookie-value/stringWithDefaultValue"))
                .andReturn();


    }

    @Test
    public void stringWithRequiredAndDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/stringWithRequiredAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("cookie-value/stringWithRequiredAndDefaultValue"))
                .andReturn();

    }

    @Test
    public void integer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/cookie-value/integer")
                        .cookie(new Cookie("id", Integer.toString(Integer.MAX_VALUE))))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("id", is(Integer.MAX_VALUE)))
                .andExpect(
                        view().name("cookie-value/integer"))
                .andReturn();
        mvcResult = this.mockMvc.perform(
                get("/cookie-value/integer")
                        .cookie(new Cookie("id", "aaa")))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(MethodArgumentTypeMismatchException.class)))
                .andReturn();

    }

}
