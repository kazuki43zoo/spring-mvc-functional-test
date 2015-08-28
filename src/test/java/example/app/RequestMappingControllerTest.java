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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
        , @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class RequestMappingControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void value() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/value"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/value"));
    }

    @Test
    public void valueOmit() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/valueOmit"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/valueOmit"));
    }

    @Test
    public void path() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/path"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/path"));
    }

    @Test
    public void getMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/get"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/get"));

    }


    @Test
    public void headMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                head("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/head"));

    }

    @Test
    public void postMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/post"));

    }

    @Test
    public void deleteMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                delete("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/delete"));

    }


    @Test
    public void putMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                put("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/put"));

    }


    @Test
    public void patchMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                patch("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/patch"));

    }


    @Test
    public void optionsMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                options("/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader("Allow"), is("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH"));

    }


    @Test
    public void traceMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.TRACE, "/request-mapping/method"))
                .andExpect(
                        status().isOk())
                .andReturn();
    }

    @Test
    public void param() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/param")
                        .param("criteria2", "")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/param"));

    }

    @Test
    public void paramMulti() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/param")
                        .param("criteria3", "")
                        .param("criteria4", "")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/paramMulti"));

    }

    @Test
    public void paramValueIsEqual() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/param")
                        .param("criteria5", "1")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/paramValueIsEqual"));

    }

    @Test
    public void paramValueIsNotEqual() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/param")
                        .param("criteria5", "10")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/paramValueIsNotEqual"));

    }


    @Test
    public void header() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/header")
                        .header("criteria2", "")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/header"));

    }

    @Test
    public void headerMulti() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/header")
                        .header("criteria3", "")
                        .header("criteria4", "")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/headerMulti"));

    }

    @Test
    public void headerValueIsEqual() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/header")
                        .header("criteria5", "1")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/headerValueIsEqual"));

    }

    @Test
    public void headerValueIsNotEqual() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/request-mapping/header")
                        .header("criteria5", "10")
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/headerValueIsNotEqual"));

    }

    @Test
    public void consume() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/consume")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/consume"));

        this.mockMvc.perform(
                post("/request-mapping/consume")
                        .contentType(MediaType.APPLICATION_XML)
        )
                .andExpect(
                        status().isUnsupportedMediaType())
                .andReturn();


    }

    @Test
    public void consumeNot() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/consume")
                        .param("not", "")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/consumeNot"));

        this.mockMvc.perform(
                post("/request-mapping/consume")
                        .param("not", "")
                        .contentType(MediaType.TEXT_XML)
        )
                .andExpect(
                        status().isUnsupportedMediaType())
                .andReturn();


    }

    @Test
    public void consumeMulti() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/consume")
                        .contentType(MediaType.TEXT_PLAIN)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/consumeMulti"));

        mvcResult = this.mockMvc.perform(
                post("/request-mapping/consume")
                        .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/consumeMulti"));


    }

    @Test
    public void produce() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/produce")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/produce"));

        this.mockMvc.perform(
                post("/request-mapping/produce")
                        .accept(MediaType.APPLICATION_XML)
        )
                .andExpect(
                        status().isNotAcceptable())
                .andReturn();


    }

    @Test
    public void produceNot() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/produce")
                        .param("not", "")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/produceNot"));

        mvcResult = this.mockMvc.perform(
                post("/request-mapping/produce")
                        .param("not", "")
                        .accept(MediaType.TEXT_XML)
        )
                .andExpect(
                        status().isNotAcceptable())
                .andReturn();
        Enumeration<String> keys = mvcResult.getRequest().getHeaderNames();

    }

    @Test
    public void produceMulti() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/request-mapping/produce")
                        .accept(MediaType.TEXT_PLAIN)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/produceMulti"));

        mvcResult = this.mockMvc.perform(
                post("/request-mapping/produce")
                        .accept(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getViewName(), is("request-mapping/produceMulti"));


    }

}
