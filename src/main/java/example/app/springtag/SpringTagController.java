package example.app.springtag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("spring-tag")
@Controller
public class SpringTagController {

	@RequestMapping("message")
	public String message(Model model) {
		Message message = new Message();
		message.setCode("guidance.passwordValidPolicy");
		message.setArguments(90, 40);
		message.setArgumentList(Arrays.asList(90, 50));
		model.addAttribute(message);
		return "spring-tag/message";
	}

	@RequestMapping("theme")
	public String theme() {
		return "spring-tag/theme";
	}

	@RequestMapping("htmlEscape")
	public String htmlEscape() {
		return "spring-tag/htmlEscape";
	}

	@RequestMapping("escapeBody")
	public String escapeBody() {
		return "spring-tag/escapeBody";
	}

	@RequestMapping("hasBindErrors")
	public String hasBindErrors(@Valid BindForm form, BindingResult result, Model model) {
		return "spring-tag/hasBindErrors";
	}

	@RequestMapping("bind")
	public String bind(@Valid BindForm form, BindingResult result, Model model) {
		result.reject("e.xx.fw.5001");
		List<LocalDate> targetDateList = new ArrayList<>();
		LocalDate date = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			targetDateList.add(date.plusDays(i));
		}
		model.addAttribute("targetDateList", targetDateList);
		return "spring-tag/bind";
	}

	@RequestMapping("url")
	public String url(Model model) {
		model.addAttribute("now", org.joda.time.LocalDate.now().toDate());
		return "spring-tag/url";
	}

	@RequestMapping("eval")
	public String eval() {
		return "spring-tag/eval";
	}

	public static class Message {
		private String code;
		private Object[] arguments;
		private List<Object> argumentList;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Object[] getArguments() {
			return arguments;
		}

		public void setArguments(Object... arguments) {
			this.arguments = arguments;
		}

		public List<Object> getArgumentList() {
			return argumentList;
		}

		public void setArgumentList(List<Object> argumentList) {
			this.argumentList = argumentList;
		}
	}

}
