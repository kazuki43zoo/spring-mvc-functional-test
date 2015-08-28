package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.app.MemberCreateForm.Card;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
@RequestMapping("members")
public class MemberController {

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "memberType=1")
    public String conform(@Valid MemberCreateForm form, BindingResult result) {
        // ...
        return "account/confirm";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "memberType!=1")
    public String cardForm(@Valid MemberCreateForm form, BindingResult result) {
        // ...
        return "account/cardForm";
    }

}
