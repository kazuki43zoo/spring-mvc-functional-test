package example.app;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;

public class AccountUpdateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    interface FreeAccount extends Default {
    }

    interface PayAccount extends Default {
    }

    @NotNull
    @Size(max = 32)
    private String name;

    @Size(min = 9, max = 11)
    private String tel;

    @Size(max = 256)
    private String streetAddress;

    @Size(max = 256)
    private String emailAddress;

    @NotNull
    @Size(min = 1, max = 1)
    private String type;

    @Null(groups = FreeAccount.class)
    @NotNull(groups = PayAccount.class)
    private String cardNo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
