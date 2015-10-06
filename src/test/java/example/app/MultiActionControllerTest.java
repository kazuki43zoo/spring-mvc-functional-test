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
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class MultiActionControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void test() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/multiaction/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("multiaction/form"))
                .andReturn();

    }

    @Test
    public void notFound() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/multiaction/aaa"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

}
