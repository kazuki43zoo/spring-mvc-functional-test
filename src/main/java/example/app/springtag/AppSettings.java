package example.app.springtag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

	@Value("${passwordValidDays:90}")
	private int passwordValidDays;

	public int getPasswordValidDays() {
		return passwordValidDays;
	}

	public void setPasswordValidDays(int passwordValidDays) {
		this.passwordValidDays = passwordValidDays;
	}

}
