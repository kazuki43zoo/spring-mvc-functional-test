package example.app.springtag;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class BindForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String text;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
