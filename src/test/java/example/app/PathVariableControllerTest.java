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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.DispatcherServlet;

import javax.inject.Inject;

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
public class PathVariableControllerTest {


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
                get("/path-variable/string/aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("path-variable/string"))
                .andReturn();


    }


    @Test
    public void stringWithName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/path-variable/stringWithName/aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("path-variable/stringWithName"))
                .andReturn();

    }

    @Test
    public void integer() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get("/path-variable/integer/" + Integer.toString(Integer.MAX_VALUE)))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("id", is(Integer.MAX_VALUE)))
                .andExpect(
                        view().name("path-variable/integer"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/path-variable/integer/aaa"))
                .andExpect(
                        status().isBadRequest())
                .andExpect(
                        request().attribute(DispatcherServlet.EXCEPTION_ATTRIBUTE, isA(MethodArgumentTypeMismatchException.class)))
                .andReturn();
    }
}
