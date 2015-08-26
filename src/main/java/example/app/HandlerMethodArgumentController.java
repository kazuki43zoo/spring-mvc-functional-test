package example.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

@RequestMapping("handler-method-argument")
@Controller
public class HandlerMethodArgumentController {

    @RequestMapping("model")
    public void model(Model model) {
        model.addAttribute("message", "call model.");
    }

    @RequestMapping("map")
    public void map(Map<String, Object> model) {
        model.put("message", "call map.");
    }

    @RequestMapping("modelMap")
    public void modelMap(ModelMap model) {
        model.put("message", "call modelMap.");
    }

    @RequestMapping("redirectAttributes")
    public String redirectAttributes(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", "1");
        redirectAttributes.addFlashAttribute("message", "call redirectAttributes.");
        return "redirect:/";
    }

    @RequestMapping("form")
    public void form(@Valid SearchForm form, BindingResult result, Model model) {
        model.addAttribute("message", "call form.");
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "validation error.");
        }
    }

    @RequestMapping("formWithErrors")
    public void formWithErrors(@Valid SearchForm form, Errors errors, Model model) {
        model.addAttribute("message", "call formWithErrors.");
        if (errors.hasErrors()) {
            model.addAttribute("errorMessage", "validation error.");
        }
    }

    @RequestMapping("locale")
    public void locale(Locale locale, Model model) {
        model.addAttribute("message", "call locale.");
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("country", locale.getCountry());
    }

    @RequestMapping("timeZone")
    public void timeZone(TimeZone timeZone, Model model) {
        model.addAttribute("message", "call timeZone.");
        model.addAttribute("timeZoneId", timeZone.getID());
    }

    @RequestMapping("zoneId")
    public void timeZone(ZoneId zoneId, Model model) {
        model.addAttribute("message", "call zoneId.");
        model.addAttribute("zoneId", zoneId.getId());
    }

    @RequestMapping("httpMethod")
    public void httpMethod(HttpMethod httpMethod, Model model) {
        model.addAttribute("message", "call httpMethod.");
        model.addAttribute("httpMethodName", httpMethod.name());
    }

    @RequestMapping("servletReqRes")
    public String servletReqRes(HttpServletRequest req, HttpServletResponse res, Model model) {
        model.addAttribute("message", "call servletReqRes.");
        res.addHeader("word", req.getParameter("word"));
        return "handler-method-argument/servletReqRes";
    }

    @RequestMapping("webRequest")
    public void webRequest(WebRequest req, Model model) {
        model.addAttribute("message", "call webRequest.");
        model.addAttribute("word", req.getParameter("word"));
    }

    @RequestMapping("nativeWebRequest")
    public void nativeWebRequest(NativeWebRequest req, Model model) {
        model.addAttribute("message", "call nativeWebRequest.");
        model.addAttribute("word", req.getParameter("word"));
    }

    @RequestMapping("session")
    public void session(HttpSession session, Model model) {
        model.addAttribute("message", "call session.");
        session.setAttribute("message", "call session.");
    }

    @RequestMapping("principal")
    public void principal(Principal principal, Model model) {
        model.addAttribute("message", "call principal.");
        model.addAttribute("principalName", principal.getName());
    }

    @RequestMapping("outputStream")
    public void outputStream(NativeWebRequest req, OutputStream outputStream, Model model) throws IOException {
        outputStream.write(("call outputStream. " + req.getParameter("word")).getBytes(StandardCharsets.UTF_8));
    }

    @RequestMapping("writer")
    public void writer(NativeWebRequest req, Writer writer, Model model) throws IOException {
        writer.write("call writer. " + req.getParameter("word"));
    }

    @RequestMapping("inputStream")
    public void inputStream(InputStream inputStream, Model model) throws IOException {
        model.addAttribute("message", "call inputStream.");
        model.addAttribute("content", StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }

    @RequestMapping("reader")
    public void reader(Reader reader, Model model) throws IOException {
        model.addAttribute("message", "call reader.");
        model.addAttribute("content", FileCopyUtils.copyToString(reader));
    }

    @ModelAttribute
    public ModelAttributeForm setUpByDefaultAttributeName() {
        ModelAttributeForm form = new ModelAttributeForm();
        form.setValue("default");
        return form;
    }

    @ModelAttribute("customModelAttributeForm")
    public ModelAttributeForm setUpByCustomAttributeName() {
        ModelAttributeForm form = new ModelAttributeForm();
        form.setValue("custom");
        return form;
    }

    @RequestMapping("modelAttributeByDefaultName")
    public void modelAttributeByDefaultName(ModelAttributeForm form, Model model) {
        model.addAttribute("message", "call modelAttributeByDefaultName.");
        model.addAttribute("value", form.getValue());
    }

    @RequestMapping("modelAttributeByCustomName")
    public void modelAttributeByCustomName(@ModelAttribute("customModelAttributeForm") ModelAttributeForm form, Model model) {
        model.addAttribute("message", "call modelAttributeByCustomName.");
        model.addAttribute("value", form.getValue());
    }

    @RequestMapping("pathVariable/{id}")
    public void pathVariable(@PathVariable Integer id, Model model) {
        model.addAttribute("message", "call pathVariable.");
        model.addAttribute("pathId", id);
    }

    @RequestMapping("matrixVariable/{id}")
    public void matrixVariable(@PathVariable Integer id, @MatrixVariable Integer x, Model model) {
        model.addAttribute("message", "call matrixVariable.");
        model.addAttribute("pathId", id);
        model.addAttribute("matrix-x", x);
    }

    @RequestMapping("requestParam")
    public void requestParam(@RequestParam Integer id, Model model) {
        model.addAttribute("message", "call requestParam.");
        model.addAttribute("paramId", id);
    }

    @RequestMapping("requestHeader")
    public void requestHeader(@RequestHeader Integer id, Model model) {
        model.addAttribute("message", "call requestHeader.");
        model.addAttribute("headerId", id);
    }

    @RequestMapping("cookieValue")
    public void cookieValue(@CookieValue Integer id, Model model) {
        model.addAttribute("message", "call cookieValue.");
        model.addAttribute("cookieId", id);
    }

    @RequestMapping("valueByPlaceholder")
    public void valueByPlaceholder(@Value("${value.id}") Integer id, Model model) {
        model.addAttribute("message", "call valueByPlaceholder.");
        model.addAttribute("valueByPlaceholderId", id);
    }

    @RequestMapping("valueBySpEL")
    public void valueBySpEL(@Value("#{systemProperties['file.encoding']}") String fileEncoding, Model model) {
        model.addAttribute("message", "call valueBySpEL.");
        model.addAttribute("spelFileEncoding", fileEncoding);
    }

    @RequestMapping("entity")
    public void entity(HttpEntity<SearchForm> entity, Model model) {
        model.addAttribute("message", "call entity.");
        model.addAttribute("content-type", entity.getHeaders().getContentType());
        model.addAttribute("word", entity.getBody().getWord());
    }

    @RequestMapping("requestBody")
    public void requestBody(@RequestBody SearchForm form, Model model) {
        model.addAttribute("message", "call entity.");
        model.addAttribute("word", form.getWord());
    }

    @RequestMapping("multipartFile")
    public void multipartFile(MultipartFile file, Model model) throws IOException {
        model.addAttribute("message", "call multipartFile.");
        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("content", StreamUtils.copyToString(file.getInputStream(), StandardCharsets.UTF_8));
    }

    @RequestMapping("multipartFileWithRequestPart")
    public void multipartFileWithRequestPart(@RequestPart("file") MultipartFile f, Model model) throws IOException {
        model.addAttribute("message", "call multipartFileWithRequestPart.");
        model.addAttribute("fileName", f.getOriginalFilename());
        model.addAttribute("content", StreamUtils.copyToString(f.getInputStream(), StandardCharsets.UTF_8));
    }

    @RequestMapping("multipartFileWithRequestParam")
    public void multipartFileWithRequestParam(@RequestParam("file") MultipartFile f, Model model) throws IOException {
        model.addAttribute("message", "call multipartFileWithRequestParam.");
        model.addAttribute("fileName", f.getOriginalFilename());
        model.addAttribute("content", StreamUtils.copyToString(f.getInputStream(), StandardCharsets.UTF_8));
    }

    @RequestMapping("part")
    public void part(Part file, Model model) throws IOException {
        model.addAttribute("message", "call part.");
        model.addAttribute("fileName", file.getSubmittedFileName());
        model.addAttribute("content", StreamUtils.copyToString(file.getInputStream(), StandardCharsets.UTF_8));
    }

    @RequestMapping("fallback")
    public void fallback(String id, Model model) throws IOException {
        model.addAttribute("message", "call fallback.");
        model.addAttribute("id", id);
    }

}
