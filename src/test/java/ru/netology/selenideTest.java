package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class selenideTest {

    @Test
    void shouldAll() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Махачкала");
        $x("//input [@placeholder='Дата встречи']").click();
        $x("//input [@placeholder='Дата встречи']").setValue("18.08.2022");
        $x("//input[@name='name']").setValue("Дарья Се-ливерстова");
        $x("//input[@maxlength='16']").setValue("+74951384061");
        $x("//span[@class='checkbox__box']").click();
        $x("//*[text()='Запланировать']").click();
        $x("//div[@data-test-id='success-notification']").
                should(visible, Duration.ofSeconds(15));

    }


}
