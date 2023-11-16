import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TarkovArenaLanguageTest extends TestBase {

    static Stream<Arguments> tarkovArenaButtonsTest() {
        return Stream.of(
                Arguments.of(Language.RU, List.of("Новости", "Поддержка", "Мерч", "Рейтинг")),
                Arguments.of(Language.EN, List.of("News", "Support", "Merch", "Rating")),
                Arguments.of(Language.DE, List.of("News", "Support", "Merch", "Ratings")),
                Arguments.of(Language.CN, List.of("新闻", "支持", "周边", "排位")),
                Arguments.of(Language.IT, List.of("Novità", "Supporto", "Merce", "Valutazione")),
                Arguments.of(Language.PL, List.of("Aktualności", "Wsparcie", "Sklep", "Ocena")),
                Arguments.of(Language.TR, List.of("Haberler", "Destek", "Merch", "Değerlendirme")),
                Arguments.of(Language.ES, List.of("Noticias", "Soporte", "Merch", "Clasificación"))
        );
    }

    @BeforeEach
    void openSite(){
        open("https://arena.tarkov.com");
    }

    @MethodSource("tarkovArenaButtonsTest")
    @ParameterizedTest(name = "Проверка отображения кнопок")
    @Tags({@Tag("WEB"), @Tag("SMOKE")})
    void tarkovArenaButtonsTest(Language language, List<String> expectedButtons) {
        $(".navBlock .selector").click();
        $$(".selector .outer .submenu .inner li" ).find(text(language.name())).click();
        $$("ul .item").filter(visible).shouldHave(texts(expectedButtons));
    }
}