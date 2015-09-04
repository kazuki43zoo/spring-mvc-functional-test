package example.app;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import example.app.AccountCreateForm.PayAccount;
import example.app.AccountCreateForm.FreeAccount;

@RequestMapping("validation")
@Controller
public class ValidationController {

    @InitBinder("accountUpdateForm")
    public void initBinderForAccountUpdateForm(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(path = "search", method = RequestMethod.GET)
    public void search(
            @Valid AccountSearchForm form,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // ...
        }
        if (result.hasFieldErrors()) {
            // ...
        }
        if (result.hasGlobalErrors()) {
            // ...
        }
        if (result.hasFieldErrors("")) {
            // ...
        }
    }

    @RequestMapping(path = "search", method = RequestMethod.GET, params = "withoutBidingResult")
    public void search(
            @Valid AccountSearchForm form,
            Model model) {
    }


    @RequestMapping(path = "create", method = RequestMethod.POST, params = {"confirm", "type=1"})
    public void confirmForFreeAccount(
            @Validated(FreeAccount.class) AccountCreateForm form,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // ...
        }
    }

    @RequestMapping(path = "create", method = RequestMethod.POST, params = {"confirm", "type=2"})
    public void confirmForPayAccount(
            @Validated(PayAccount.class) AccountCreateForm form,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            // ...
        }
    }

    @RequestMapping(path = "update", method = RequestMethod.POST, params = "type=1")
    public void updateForFreeAccount(
            @Validated(FreeAccount.class) AccountUpdateForm form,
            BindingResult result) {

    }

    @RequestMapping(path = "update", method = RequestMethod.POST, params = "type=2")
    public void updateForPayAccount(
            @Validated(PayAccount.class) AccountUpdateForm form,
            BindingResult result) {

    }

}
