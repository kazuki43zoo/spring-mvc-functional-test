package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("accounts")
public class AccountController {

    @RequestMapping({"me/email", "my/email"})
    public String email(Model model) {
        // ...
        return "account/email";
    }

    @RequestMapping("{accountId}")
    public String account(@PathVariable String accountId, Model model) {
        // ...
        return "account/email";
    }

    @RequestMapping(path = "{accountId}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String getAccount(@PathVariable String accountId, Model model) {
        // ...
        return "account/email";
    }

    @RequestMapping(path = "create", params = "form")
    public String form(Model model) {
        model.addAttribute(new AccountCreateForm());
        return "account/form";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "confirm")
    public String confirm(@Valid AccountCreateForm form, BindingResult result) {
        // ...
        return "account/confirm";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "redo")
    public String redo(AccountCreateForm form) {
        return "account/form";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, params = "create")
    public String create(@Valid AccountCreateForm form, BindingResult result,
                         RedirectAttributes redirectAttributes) {
        // ...
        return "redirect:/accounts/create?complete";
    }

    @RequestMapping(path = "create", method = RequestMethod.GET, params = "complete")
    public String complete() {
        return "account/complete";
    }

}
