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
        //$x("//input [@placeholder='Дата встречи']").click();
        $x("//input [@placeholder='Дата встречи']").doubleClick();
        $x("//input [@placeholder='Дата встречи']").sendKeys(" ");
        $x("//input [@placeholder='Дата встречи']").setValue(planningDate);
        $x("//input[@name='name']").setValue("Дарья Се-ливерстова");
        $x("//input[@maxlength='16']").setValue("+74951384061");
        $x("//span[@class='checkbox__box']").click();
        $x("//*[text()='Запланировать']").click();
        $x("//div[@data-test-id='success-notification']").
                should(visible, Duration.ofSeconds(15));
         //$x("//div[@class='notification__content']")
                //.filter(visible).first()
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }


}

