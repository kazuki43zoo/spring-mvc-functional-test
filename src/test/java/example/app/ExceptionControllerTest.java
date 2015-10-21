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
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
		, @ContextConfiguration("classpath:META-INF/spring/spring-mvc.xml")
})
public class ExceptionControllerTest {


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
				get("/exception/status"))
				.andExpect(status().isNotFound())
				.andExpect(status().reason("test"))
				.andReturn();

	}

	@Test
	public void testCause() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(
				get("/exception/status/cause"))
				.andExpect(status().isNotFound())
				.andExpect(status().reason("test"))
				.andReturn();

	}

	@Test
	public void testExtends() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(
				get("/exception/status/extends"))
				.andExpect(status().isNotFound())
				.andExpect(status().reason("test"))
				.andReturn();

	}

	@Test
	public void testThrow() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(
				get("/exception/throw"))
				.andExpect(status().isInternalServerError())
				.andExpect(model().attribute("message", "test message"))
				.andExpect(view().name("error/systemError"))
				.andReturn();

	}

}
