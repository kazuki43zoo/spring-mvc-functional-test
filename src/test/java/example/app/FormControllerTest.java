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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class FormControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void createConfirm() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/form/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("confirm", "")
                        .param("name", "aaaa")
                        .param("tel", "09012345678")
                        .param("streetAddress", "東京")
                        .param("emailAddress", "spring@test.com"))
                .andExpect(
                        status().isOk())
                .andReturn();

        AccountCreateForm form = AccountCreateForm.class.cast(mvcResult.getModelAndView().getModelMap().get("accountCreateForm"));
        assertThat(form.getName(), is("aaaa"));
        assertThat(form.getTel(), is("09012345678"));
        assertThat(form.getStreetAddress(), is("東京"));
        assertThat(form.getEmailAddress(), is("spring@test.com"));
        assertThat(mvcResult.getModelAndView().getViewName(), is("form/createConfirm"));

    }

}
