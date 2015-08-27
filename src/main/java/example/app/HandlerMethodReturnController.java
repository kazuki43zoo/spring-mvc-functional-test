package example.app;

import example.domain.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@RequestMapping("handler-method-return")
@Controller
public class HandlerMethodReturnController {

    @RequestMapping("string")
    public String string() {
        return "handler-method-return/viewName";
    }

    @RequestMapping("modelAndView")
    public ModelAndView modelAndView() {
        ModelAndView mva = new ModelAndView("handler-method-return/modelAndView");
        SearchForm searchForm = new SearchForm();
        searchForm.setWord("modelAndView");
        mva.addObject(searchForm);
        return mva;
    }

    @RequestMapping("voidMethod")
    public void voidMethod() {
    }

    @RequestMapping("responseEntity")
    public ResponseEntity<Account> responseEntity() {

        Account account = new Account();
        account.setId("ID001");
        account.setName("Yamada");

        return ResponseEntity.ok()
                .header("X-Track", "12345")
                .body(account);
    }

    @RequestMapping("httpHeaders")
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Track", "12345");
        return headers;
    }

    @RequestMapping("javaBeans")
    public SearchForm javaBeans() {
        SearchForm searchForm = new SearchForm();
        searchForm.setWord("javaBeans");
        return searchForm;
    }

    @RequestMapping("javaBeansWithModelAttribute")
    @ModelAttribute("customSearchForm")
    public SearchForm javaBeansWithModelAttribute() {
        SearchForm searchForm = new SearchForm();
        searchForm.setWord("javaBeansWithModelAttribute");
        return searchForm;
    }

    @RequestMapping("responseBody")
    @ResponseBody
    public Account responseBody() {

        Account account = new Account();
        account.setId("ID001");
        account.setName("Yamada");

        return account;
    }


}
