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
public class HandlerMethodArgumentControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void model() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/model"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call model."));
    }

    @Test
    public void map() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/map"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call map."));
    }

    @Test
    public void modelMap() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/modelMap"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call modelMap."));
    }

    @Test
    public void redirectAttributes() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/redirectAttributes"))
                .andExpect(
                        status().isFound())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("id"), is("1"));
        assertThat(mvcResult.getFlashMap().get("message"), is("call redirectAttributes."));
        assertThat(mvcResult.getResponse().getRedirectedUrl(), is("/?id=1"));
    }

    @Test
    public void form() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/form").param("word", "aaa"))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        BindingResult result = BindingResult.class.cast(mvcResult.getModelAndView().getModelMap().get(BindingResult.MODEL_KEY_PREFIX + "searchForm"));
        assertThat(form.getWord(), is("aaa"));
        assertThat(result.hasErrors(), is(false));
        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call form."));
    }

    @Test
    public void formWithValidationError() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/form").param("word", ""))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        BindingResult result = BindingResult.class.cast(mvcResult.getModelAndView().getModelMap().get(BindingResult.MODEL_KEY_PREFIX + "searchForm"));
        assertThat(form.getWord(), is(""));
        assertThat(result.hasErrors(), is(true));
        assertThat(result.hasFieldErrors("word"), is(true));
        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call form."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("errorMessage"), is("validation error."));
    }

    @Test
    public void formWithErrors() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/formWithErrors").param("word", "aaa"))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        BindingResult result = BindingResult.class.cast(mvcResult.getModelAndView().getModelMap().get(BindingResult.MODEL_KEY_PREFIX + "searchForm"));
        assertThat(form.getWord(), is("aaa"));
        assertThat(result.hasErrors(), is(false));
        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call formWithErrors."));
    }

    @Test
    public void formWithErrorsWithValidationError() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/formWithErrors").param("word", ""))
                .andExpect(
                        status().isOk())
                .andReturn();

        SearchForm form = SearchForm.class.cast(mvcResult.getModelAndView().getModelMap().get("searchForm"));
        BindingResult result = BindingResult.class.cast(mvcResult.getModelAndView().getModelMap().get(BindingResult.MODEL_KEY_PREFIX + "searchForm"));
        assertThat(form.getWord(), is(""));
        assertThat(result.hasErrors(), is(true));
        assertThat(result.hasFieldErrors("word"), is(true));
        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call formWithErrors."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("errorMessage"), is("validation error."));
    }


    @Test
    public void locale() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/locale").locale(Locale.JAPAN))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call locale."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("language"), is("ja"));
        assertThat(mvcResult.getModelAndView().getModelMap().get("country"), is("JP"));

    }


    @Test
    public void timeZone() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/timeZone").locale(Locale.JAPAN))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call timeZone."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("timeZoneId"), is("Asia/Tokyo"));

    }

    @Test
    public void zoneId() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/zoneId").locale(Locale.JAPAN))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call zoneId."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("zoneId"), is("Asia/Tokyo"));

    }


    @Test
    public void httpMethod() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/httpMethod").locale(Locale.JAPAN))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call httpMethod."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("httpMethodName"), is("GET"));

    }

    @Test
    public void servletReqRes() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/servletReqRes").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call servletReqRes."));
        assertThat(mvcResult.getResponse().getHeader("word"), is("test"));
        assertThat(mvcResult.getRequest().getSession(false), nullValue());

    }

    @Test
    public void webRequest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/webRequest").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call webRequest."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("word"), is("test"));

    }

    @Test
    public void nativeWebRequest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/nativeWebRequest").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call nativeWebRequest."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("word"), is("test"));

    }


    @Test
    public void session() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/session").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call session."));
        assertThat(mvcResult.getRequest().getSession().getAttribute("message"), is("call session."));
        assertThat(mvcResult.getRequest().getSession().isNew(), is(true));

    }

    @Test
    public void principal() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/principal").principal(new UsernamePasswordAuthenticationToken("kazuki43zoo", "password")))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call principal."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("principalName"), is("kazuki43zoo"));

    }


    @Test
    public void outputStream() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/outputStream").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(), is("call outputStream. test"));

    }

    @Test
    public void writer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/writer").param("word", "test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(), is("call writer. test"));

    }


    @Test
    public void inputStream() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/handler-method-argument/inputStream").content("test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call inputStream."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("test"));


    }


    @Test
    public void reader() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/handler-method-argument/reader").content("test"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call reader."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("test"));

    }

    @Test
    public void modelAttributeByDefaultName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/modelAttributeByDefaultName"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call modelAttributeByDefaultName."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("value"), is("default"));

    }

    @Test
    public void modelAttributeByCustomName() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/modelAttributeByCustomName"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call modelAttributeByCustomName."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("value"), is("custom"));

    }

    @Test
    public void pathVariable() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/pathVariable/1"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call pathVariable."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("pathId"), is(1));

    }

    @Test
    public void matrixVariable() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/matrixVariable/1;x=90"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call matrixVariable."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("pathId"), is(1));
        assertThat(mvcResult.getModelAndView().getModelMap().get("matrix-x"), is(90));

    }

    @Test
    public void requestParam() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/requestParam").param("id", "1"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call requestParam."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("paramId"), is(1));

    }

    @Test
    public void requestHeader() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/requestHeader").header("id", "1"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call requestHeader."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("headerId"), is(1));

    }

    @Test
    public void cookieValue() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/cookieValue").cookie(new Cookie("id", "1")))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call cookieValue."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("cookieId"), is(1));

    }

    @Test
    public void valueByPlaceholder() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/valueByPlaceholder"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call valueByPlaceholder."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("valueByPlaceholderId"), is(1));

    }

    @Test
    public void valueBySpEL() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/valueBySpEL"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call valueBySpEL."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("spelFileEncoding"), is(System.getProperty("file.encoding")));

    }

    @Test
    public void entity() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/handler-method-argument/entity")
                        .content("{\"word\":\"test\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call entity."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content-type"), is(MediaType.APPLICATION_JSON));
        assertThat(mvcResult.getModelAndView().getModelMap().get("word"), is("test"));

    }


    @Test
    public void requestBody() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/handler-method-argument/requestBody")
                        .content("{\"word\":\"test\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call entity."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("word"), is("test"));

    }


    @Test
    public void multipartFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "kazuki43zoo,Kazuki Shimizu".getBytes(StandardCharsets.UTF_8));
        MvcResult mvcResult = this.mockMvc.perform(
                fileUpload("/handler-method-argument/multipartFile")
                        .file(file))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call multipartFile."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("fileName"), is("test.csv"));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("kazuki43zoo,Kazuki Shimizu"));

    }

    @Test
    public void multipartFileWithRequestPart() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "kazuki43zoo,Kazuki Shimizu".getBytes(StandardCharsets.UTF_8));
        MvcResult mvcResult = this.mockMvc.perform(
                fileUpload("/handler-method-argument/multipartFileWithRequestPart")
                        .file(file))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call multipartFileWithRequestPart."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("fileName"), is("test.csv"));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("kazuki43zoo,Kazuki Shimizu"));

    }

    @Test
    public void multipartFileWithRequestParam() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "kazuki43zoo,Kazuki Shimizu".getBytes(StandardCharsets.UTF_8));
        MvcResult mvcResult = this.mockMvc.perform(
                fileUpload("/handler-method-argument/multipartFileWithRequestParam")
                        .file(file))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call multipartFileWithRequestParam."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("fileName"), is("test.csv"));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("kazuki43zoo,Kazuki Shimizu"));

    }

    @Test
    public void part() throws Exception {
        Part part = mock(Part.class);
        when(part.getName()).thenReturn("file");
        when(part.getInputStream()).thenReturn(new ByteArrayInputStream("kazuki43zoo,Kazuki Shimizu".getBytes(StandardCharsets.UTF_8)));
        when(part.getSubmittedFileName()).thenReturn("test.csv");
        MvcResult mvcResult = this.mockMvc.perform(
                fileUpload("/handler-method-argument/part").with((request) -> {
                    request.addPart(part);
                    return request;
                }))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call part."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("fileName"), is("test.csv"));
        assertThat(mvcResult.getModelAndView().getModelMap().get("content"), is("kazuki43zoo,Kazuki Shimizu"));

    }

    @Test
    public void fallbackByRequestParam() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/fallback").param("id", "0001"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call fallback."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("id"), is("0001"));

    }

    @Test
    public void fallbackByRequestHeader() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                get("/handler-method-argument/fallback").header("id", "0001"))
                .andExpect(
                        status().isOk())
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModelMap().get("message"), is("call fallback."));
        assertThat(mvcResult.getModelAndView().getModelMap().get("id"), nullValue());

    }


}
