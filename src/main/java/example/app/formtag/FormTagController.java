package example.app.formtag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("formtag")
@Controller
public class FormTagController {

	@ModelAttribute
	public SampleForm setUpSampleForm() {
		return new SampleForm();
	}

	@RequestMapping("form")
	public String form() {
		return "form-tag/form";
	}

}
