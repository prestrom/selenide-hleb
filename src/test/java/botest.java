import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import helpers.Util;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.util.Random;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class botest {

private String corpName, corpStatus;
@BeforeClass
public static void beforeClass() {
    String login = "ce";
    String password = "password";
    Configuration.baseUrl="https://qa-bo.statecap.co/backoffice";
    Configuration.holdBrowserOpen = true;
 open("/login");
 $(By.id("userName")).setValue(login);
 $(By.id("password")).setValue(password);
 $(byValue("Login")).click();
    }

@Test
public void mainPageTest() {
    $(By.id("logo")).should(exist);

}

@Test
    public void corpCardEditTest() {
    open("/corporation/maintenance");
    $(By.id("lookBy")).selectOption("Corporation Number");
    $(By.id("criteria")).selectOption("Any Part of Field");
    $(By.id("searchValue")).setValue("1");
    $(By.name("findBtn")).click(); // search for companies list
        Util.waitForAjaxCompleted();
    ElementsCollection allLinks = $$(By.className("editCorporation"))
            .excludeWith(attribute("title", "Edit"))
            .excludeWith(attribute("title", "New")); // collect all company name links into list


    Util.getRandomElementFromList(allLinks).click(); // click random company list
    Util.waitForAjaxCompleted();
    $(By.id("corporationEditBlock")).is(visible);
    corpName = $(By.name("corpname")).getAttribute("value")+" Selenide Test";
    $(By.name("corpname")).setValue(corpName);
    $(By.name("corpno")).setValue("0123456");
    Util.getRandomOption($(By.name("incstate")));
    $(By.name("incdate")).setValue(Util.getCurrentDate());
    $(By.name("corpinfo")).setValue("Selenide Test Corp info");
    Util.getRandomOption($(Selectors.byAttribute("name*", "statute")));
    $(Selectors.byAttribute("id*", "updateLastStatusReport")).click();
    $(By.id("lastStatusReport1")).shouldHave(text(Util.getCurrentDateShortened()));
    $(By.name("corpstat")).setValue("Corp Status Selenide Test");
    $(By.name("agent")).setValue("Agent Selenide Test");
    $(By.name("agentAddress")).setValue("Agent address Selenide Test");
    $(By.name("officersDirectors")).setValue("Officers & Directors Selenide Test");
    if ($(By.id("ordBlock")).is(hidden)) {
        $(By.name("tcyes")).click();
    }
    $(By.name("orddate")).setValue(Util.getCurrentDate());
    $(By.name("ordcust")).setValue("ord customer Selenide Test");
    $(Selectors.byAttribute("id*", "updateLastTaxReport")).click();
    $(By.id("lastTaxReport1")).shouldHave(text(Util.getCurrentDateShortened()));
    $(By.name("taxInformation")).setValue("Tax Info Selenide Test");
    $(By.name("miscnotes")).setValue("testNote for Selenide Test");
    $(By.name("save")).click();
    Util.waitForAjaxCompleted();
    $(By.className("t"+"-"+"message"+"-"+"container")).should(appear)
            .shouldHave(text("The corporation " + corpName + " was edited.")).shouldNot(visible);
}




}
