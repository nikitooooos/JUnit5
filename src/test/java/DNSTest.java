import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DNSTest extends TestBase {

    @BeforeEach
    void openSite(){
        open("https://www.dns-shop.ru/");
    }

    @CsvSource({
            "Бытовая техника, Техника для кухни",
            "Красота и здоровье, 'Уход за телом",
            "Смартфоны и фототехника,  'Фототехника"
    })
    @ParameterizedTest(name="Надписи {1} верно отображаются для категории {0}")
    void DNSbuttonsCheck(String category, String buttons){
        $$(".homepage__catalog").findBy(text(category)).click();
        $(".content h1").shouldHave(text(buttons));
    }
}
