package example.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class HandlerMethodReturnControllerTest {


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
                get("/handler-method-return/string"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("handler-method-return/viewName"));
    }

    @Test
    public void modelAndView() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/modelAndView"))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        assertThat(form.getWord(), is("modelAndView"));
        assertThat(mvcResult.getModelAndView().getViewName(), is("handler-method-return/modelAndView"));
    }

    @Test
    public void voidMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/voidMethod"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("handler-method-return/voidMethod"));
    }

    @Test
    public void responseEntity() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/responseEntity"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(), is("{\"id\":\"ID001\",\"name\":\"Yamada\"}"));
        assertThat(mvcResult.getResponse().getHeader("X-Track"), is("12345"));

    }

    @Test
    public void httpHeaders() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/httpHeaders"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(), is(""));
        assertThat(mvcResult.getResponse().getHeader("X-Track"), is("12345"));

    }
    @Test
    public void responseBody() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/responseBody"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(), is("{\"id\":\"ID001\",\"name\":\"Yamada\"}"));

    }

    @Test
    public void javaBeans() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/javaBeans"))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        assertThat(form.getWord(), is("javaBeans"));
        assertThat(mvcResult.getModelAndView().getViewName(), is("handler-method-return/javaBeans"));
    }

    @Test
    public void javaBeansWithModelAttribute() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-return/javaBeansWithModelAttribute"))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("customSearchForm"));
        assertThat(form.getWord(), is("javaBeansWithModelAttribute"));
        assertThat(mvcResult.getModelAndView().getViewName(), is("handler-method-return/javaBeansWithModelAttribute"));
    }
}
