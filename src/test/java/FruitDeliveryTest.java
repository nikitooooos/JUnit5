import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FruitDeliveryTest extends TestBase {
    @BeforeEach
    void openSite(){
        open("https://www.fruktinadom.ru/");
    }

    @CsvSource({
            "Корзина, Корзина",
            "Контакты,Контакты/Реквизиты",
    })
    @ParameterizedTest(name="Надпись {1} верно отображается для категории {0}")
    @Tag("Buttons")
    void fruitButtonsCheck(String category, String buttons){
        $(".header__menu").$(byText(category)).click();
        $("#page__title").shouldHave(text(buttons));
    }
}
