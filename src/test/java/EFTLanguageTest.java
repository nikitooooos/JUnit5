import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EFTLanguageTest extends TestBase {

    static Stream<Arguments> escapeFromTarkovButtonsTest() {
        return Stream.of(
                Arguments.of(Language.Русский, List.of("Новости", "Поддержка", "Мерч", "Рейтинг")),
                Arguments.of(Language.English, List.of("News", "Support", "Merch", "Rating")),
                Arguments.of(Language.Deutsch, List.of("News", "Support", "Merch", "Ratings")),
                Arguments.of(Language.中文, List.of("新闻", "支持", "周边", "排位")),
                Arguments.of(Language.Italiano, List.of("Novità", "Supporto", "Merce", "Valutazione")),
                Arguments.of(Language.Polski, List.of("Aktualności", "Wsparcie", "Sklep", "Ocena")),
                Arguments.of(Language.Türkçe, List.of("Haberler", "Destek", "Merch", "Değerlendirme")),
                Arguments.of(Language.Español, List.of("Noticias", "Soporte", "Merch", "Clasificación"))
        );
    }

    @BeforeEach
    void openSite(){
        open("https://arena.tarkov.com");
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка отображения кнопок")
    void escapeFromTarkovButtonsTest(Language language, List<String> expectedButtons) {
        $(".selector").click();
        $$(".submenu" ).find(text(language.name())).click();
        $$(".main_menu").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));
    }
}
