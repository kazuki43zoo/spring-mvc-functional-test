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
public class ValidationControllerTest {


    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void searchWithoutBindingResult() throws Exception {
        this.mockMvc.perform(
                get("/validation/search")
                        .param("withoutBidingResult", "")
                        .param("tel", "09012345678sdfasdfsdf")
        )
                .andExpect(
                        status().isBadRequest()
                )
                .andReturn();

    }

    @Test
    public void createConfirmForFreeAccount() throws Exception {
        this.mockMvc.perform(
                post("/validation/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("confirm", "")
                        .param("name", "aaaa")
                        .param("tel", "09012345678")
                        .param("streetAddress", "東京")
                        .param("type", "1")
                        .param("emailAddress", "spring@test.com")
                        .param("cardNo", "aaa")
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("validation/create")
                )
                .andExpect(
                        model().attributeHasFieldErrorCode("accountCreateForm", "cardNo", "Size")
                )
                .andReturn();

    }

    @Test
    public void createConfirmForPayAccount() throws Exception {
        this.mockMvc.perform(
                post("/validation/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("confirm", "")
                        .param("name", "aaaa")
                        .param("tel", "09012345678")
                        .param("streetAddress", "東京")
                        .param("type", "2")
                        .param("emailAddress", "spring@test.com")
                        .param("cardNo", "")
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("validation/create")
                )
                .andExpect(
                        model().attributeHasFieldErrorCode("accountCreateForm", "cardNo", "Size")
                )
                .andReturn();
    }


    @Test
    public void updateForFreeAccount() throws Exception {
        this.mockMvc.perform(
                post("/validation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("tel", "")
                        .param("streetAddress", "東京")
                        .param("type", "1")
                        .param("emailAddress", "spring@test.com")
                        .param("cardNo", "aaa")
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("validation/update")
                )
                .andExpect(
                        model().attributeHasFieldErrorCode("accountUpdateForm", "name", "NotNull")
                )
                .andExpect(
                        model().attributeHasFieldErrorCode("accountUpdateForm", "cardNo", "Null")
                )
                .andReturn();

    }
    @Test
    public void updateForFreeAccount2() throws Exception {
        this.mockMvc.perform(
                post("/validation/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "aa")
                        .param("tel", "12345678")
                        .param("streetAddress", "東京")
                        .param("type", "1")
                        .param("emailAddress", "spring@test.com")
                        .param("cardNo", "")
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("validation/update")
                )
                .andExpect(
                        model().attributeHasFieldErrorCode("accountUpdateForm", "tel", "Size")
                )
                .andReturn();

    }
}
