import com.codeborne.selenide.Condition;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byValue;

public class botest {
    String login = "ce";
    String password = "password";
    boolean result;

@Before
public void loggedin() {
 open("https://qa-bo.statecap.co/backoffice/login");
 $(By.id("userName")).setValue(login);
 $(By.id("password")).setValue(password);
 $(byValue("Login")).click();
    }

@Test
public void mainPageTest() {
    $(By.id("logo")).should(Condition.exist);

}




}
