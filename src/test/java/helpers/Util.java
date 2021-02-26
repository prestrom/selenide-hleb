package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.$;
public final class Util {
    public static void waitForAjaxCompleted() {
        Configuration.timeout = 45000;
        sleep(1000);
        $(By.className("zone-ajax-overlay")).should(Condition.disappear);
    }

    public static  <T> T getRandomElementFromList(List<? extends T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static int getRandomInt (int i) {
        Random rand  = new Random();
        return rand.nextInt(i);
    }


    public static void getRandomOption (SelenideElement element) {
        Select select = new Select(element);
        int optionSize = select.getOptions().size();
        Random rand = new Random();
        element.selectOption(rand.nextInt(optionSize));

    }
    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }
    public static String getCurrentDateShortened() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YY");
        return dateFormat.format(date);
    }
}
