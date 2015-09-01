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

import java.sql.Date;
import java.time.LocalDate;

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
public class InitBinderControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void date() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/init-binder/searchUsingCustomFormatter")
                        .param("dateWithCustomFormatter", "2015-09-01"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("date", is(Date.valueOf(LocalDate.of(2015, 9, 1)))))
                .andExpect(
                        view().name("init-binder/searchUsingCustomFormatter"))
                .andReturn();
    }


}
