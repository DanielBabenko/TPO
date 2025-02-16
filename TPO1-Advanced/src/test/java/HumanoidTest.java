import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.*;

import static org.junit.jupiter.api.Assertions.*;
import static task1.Tangent.tangentTaylor;
import static task3.Clothes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class HumanoidTest {

    private Humanoid humanoid;
    private Location cabin;

    @BeforeEach
    void setUp() {
        humanoid = new Humanoid("Форд");
        cabin = new Location("Каюта");
        humanoid.setClothes(Arrays.asList(UNDERWEAR, SHOES));
    }

    @Test
    void testSleepWithMuse() {
        humanoid.setHasMuse(true);
        assertFalse(humanoid.sleep(), "Должен вернуть false, если есть муза.");
    }

    @Test
    void testSleepWithoutMuse() {
        humanoid.setHasMuse(false);
        assertTrue(humanoid.sleep(), "Должен вернуть true, если нет музы.");
    }

    @Test
    void testNegativeVolume() {
        Computer computer = new Computer(Size.SMALL, cabin, 100, true);
        humanoid.setHasMuse(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Article article = humanoid.writeAnArticle("Test Theme", computer, Genres.TOXIC, -10);
                }
        );

        String expectedMessage = "Статья должна занимать хотя бы 1 страницу.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Некорректная ошибка.");
    }

    @Test
    void testWriteAnArticleSuccessful() throws Exception {
        Computer computer = new Computer(Size.SMALL, cabin);
        computer.chargeFull();
        humanoid.setHasMuse(true);

        Article article = humanoid.writeAnArticle("Test Theme", computer, Genres.TOXIC, 10);

        assertNotNull(article, "Должен вернуть Article, если компьютер работает и есть муза.");
        assertEquals("Test Theme", article.getTopic(), "Тема статьи должна соответствовать заданной.");
        assertEquals(10, article.getVolume(), "Объем статьи должен соответствовать заданному.");
        assertEquals(90, computer.getBatteryCharge(), "Заряд должен соответствовать затраченному.");
    }

    @Test
    void testWriteAnArticleWithLowBattery() throws Exception {
        Computer computer = new Computer(Size.SMALL, cabin);
        computer.setBatteryCharge(8);
        humanoid.setHasMuse(true);

        Article article = humanoid.writeAnArticle(52, computer, Genres.TOXIC, 10);

        assertNotNull(article, "Должен вернуть Article, если компьютер работает и есть муза.");
        assertEquals(52, article.getTopic(), "Тема статьи должна соответствовать заданной.");
        assertEquals(8, article.getVolume(), "Объем статьи должен соответствовать заданному.");
        assertEquals(0, computer.getBatteryCharge(), "Заряд должен соответствовать затраченному.");
    }

    @Test
    void testWriteAnArticleComputerNotFunctional() {
        Computer computer = new Computer(Size.SMALL, cabin, false);
        computer.chargeFull();
        humanoid.setHasMuse(true);

        Exception exception = assertThrows(Exception.class, () -> {
            humanoid.writeAnArticle("Test Theme", computer, Genres.FANTASY, 100);
        });

        assertEquals("Не сегодня.", exception.getMessage(), "Сообщение об ошибке должно соответствовать.");
    }

    @Test
    void testWriteAnArticleHasMuse() {
        Computer computer = new Computer(Size.SMALL, cabin);
        computer.chargeFull();
        humanoid.setHasMuse(false);

        Exception exception = assertThrows(Exception.class, () -> {
            humanoid.writeAnArticle("Test Theme", computer, Genres.FANTASY, 100);
        });

        assertEquals("Не сегодня.", exception.getMessage(), "Сообщение об ошибке должно соответствовать.");
    }

    @Test
    void testPublishAnArticle() throws Exception {
        Article article = new Article(Genres.SCIENTIFIC, humanoid, 50, "Hamza krutoi");
        Guide magazine = new Guide("Forbes");

        humanoid.publishAnArticle(article, magazine);

        assertTrue(magazine.articles.contains(article), "Журнал должен содержать опубликованную статью.");
    }

    @Test
    void testPublishADraft() throws Exception {
        Computer computer = new Computer(Size.SMALL, cabin);
        computer.setBatteryCharge(1);
        humanoid.setHasMuse(true);

        Article article = humanoid.writeAnArticle("Hamza Loxx", computer, Genres.MYSTERY, 7);
        Guide magazine = new Guide("Playboy");

        Exception exception = assertThrows(Exception.class, () -> {
            humanoid.publishAnArticle(article, magazine);
        });

        assertEquals("Черновик нужно довести до готовой статьи, чтобы опубликовать!", exception.getMessage(), "Должна вызываться соответствующая ошибка.");
    }

    @Test
    void testPublishFinishedDraft() throws Exception {
        Computer computer = new Computer(Size.SMALL, cabin);
        computer.setBatteryCharge(1);
        humanoid.setHasMuse(true);

        Article draft = humanoid.writeAnArticle("Hamza Loxx", computer, Genres.MYSTERY, 7);
        Article article = humanoid.finishAnArticle((Draft) draft, 6);
        Guide magazine = new Guide("Tyoschin Yazik");

        humanoid.publishAnArticle(article, magazine);

        assertTrue(magazine.articles.contains(article), "Журнал должен содержать опубликованную статью.");
    }

    @Test
    void testChangeDislocation() {
        Location newLocation = new Location("Мостик");

        humanoid.changeDislocation(newLocation);

        assertEquals(newLocation, humanoid.getLocation(), "Местоположение должно соответствовать заданному.");
    }

    @Test
    void testPutOnClothes() {
        Clothes clothes = SWEATER;

        humanoid.setClothes(new ArrayList<>()); // Инициализируем список clothes
        humanoid.putOnClothes(clothes);

        assertTrue(humanoid.getClothes().contains(clothes), "Список одежды должен содержать надетую одежду.");
    }

    @Test
    void testPutOffClothesSuccessful() {
        Clothes clothes = COAT;
        List<Clothes> clothesList = new ArrayList<>();
        clothesList.add(clothes);
        humanoid.setClothes(clothesList);

        humanoid.putOffClothes(clothes);

        assertFalse(humanoid.getClothes().contains(clothes), "Список одежды не должен содержать снятую одежду.");
    }

    @Test
    void testPutOffClothesEmptyList() {
        Clothes clothes = SHIRT;
        humanoid.setClothes(new ArrayList<>());

        humanoid.putOffClothes(clothes);

        assertTrue(humanoid.getClothes().isEmpty(), "Список одежды должен оставаться пустым.");
    }
}