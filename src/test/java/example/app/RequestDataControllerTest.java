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
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.Enumeration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class RequestDataControllerTest {


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


    @Test
    public void requestparamString() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/string")
                        .param("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("requestparam/string"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/requestparam/string"))
                .andExpect(
                        status().isBadRequest())
                .andReturn();

    }


    @Test
    public void requestparamStringWithName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/stringWithName")
                        .param("word", "aaaa"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("aaaa")))
                .andExpect(
                        view().name("requestparam/stringWithName"))
                .andReturn();

    }

    @Test
    public void requestparamStringWithRequired() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/stringWithRequired"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("word is null")))
                .andExpect(
                        view().name("requestparam/stringWithRequired"))
                .andReturn();

    }

    @Test
    public void requestparamStringWithDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/stringWithDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("requestparam/stringWithDefaultValue"))
                .andReturn();

        mvcResult = this.mockMvc.perform(
                get("/requestparam/stringWithDefaultValue")
                        .param("word", ""))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("requestparam/stringWithDefaultValue"))
                .andReturn();


    }

    @Test
    public void requestparamStringWithRequiredAndDefaultValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/stringWithRequiredAndDefaultValue"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("word", is("*")))
                .andExpect(
                        view().name("requestparam/stringWithRequiredAndDefaultValue"))
                .andReturn();

    }

    @Test
    public void requestparamInteger() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/requestparam/integer")
                        .param("id", Integer.toString(Integer.MAX_VALUE)))
                .andExpect(
                        status().isOk())
                .andExpect(
                        model().attribute("id", is(Integer.MAX_VALUE)))
                .andExpect(
                        view().name("requestparam/integer"))
                .andReturn();

    }
}
