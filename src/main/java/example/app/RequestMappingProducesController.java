package example.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "request-mapping/produce", produces = MediaType.APPLICATION_XML_VALUE)
@Controller
public class RequestMappingProducesController {

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void produce() {
    }

    @RequestMapping(params = "not", produces = "!"+MediaType.TEXT_XML_VALUE)
    public String produceNot() {
        return "request-mapping/produceNot";
    }

    @RequestMapping(produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.TEXT_HTML_VALUE})
    public String produceMulti() {
        return "request-mapping/produceMulti";
    }


}
