package example.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("form")
@Controller
public class FormController {

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "confirm")
    public String createConfirm(AccountCreateForm from) {
        return "form/createConfirm";
    }

}
