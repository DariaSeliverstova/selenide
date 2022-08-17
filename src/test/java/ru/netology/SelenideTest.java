package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static javax.print.attribute.standard.MediaSizeName.A;
import java.time.LocalDate;


public class SelenideTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldAll() {
        String planningDate = generateDate(5);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Махачкала");
        $x("//input [@placeholder='Дата встречи']").doubleClick();
        $x("//input [@placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $x("//input [@placeholder='Дата встречи']").setValue(planningDate);
        $x("//input[@name='name']").setValue("Дарья Се-ливерстова");
        $x("//input[@name='phone']").setValue("+74951384061");
        $x("//span[@class='checkbox__box']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@class='notification notification_visible notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']").
                should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }


}