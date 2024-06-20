import org.example.mappers.CardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ValueFromStringToIntTest {

    private final CardMapper cardMapper = new CardMapper();


    @Test
    void testValueFromStringToInt_NullValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cardMapper.valueFromStringToInt(null));
    }

    @Test
    void testValueFromStringToInt_EmptyValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cardMapper.valueFromStringToInt(""));
    }

    @Test
    void testValueFromStringToInt_IllegalValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cardMapper.valueFromStringToInt("xxfrrt"));
    }

    @Test
    void testValueFromStringToInt_IllegalValue2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cardMapper.valueFromStringToInt("685"));
    }

    @Test
    void testValueFromStringToIntHappyPath() {
        String testJson = "6";
        Assertions.assertEquals(6, cardMapper.valueFromStringToInt(testJson));
    }

    @Test
    void testValueFromStringToIntHappyPath1() {
        String testJson = "KinG";
        Assertions.assertEquals(13, cardMapper.valueFromStringToInt(testJson));
    }

    @Test
    void testValueFromStringToIntHappyPath2() {
        String testJson = "ACE";
        Assertions.assertEquals(14, cardMapper.valueFromStringToInt(testJson));
    }

    @Test
    void testValueFromStringToIntHappyPath3() {
        String testJson = "qUEen";
        Assertions.assertEquals(12, cardMapper.valueFromStringToInt(testJson));
    }

    @Test
    void testValueFromStringToIntHappyPath4() {
        String testJson = "jACk";
        Assertions.assertEquals(11, cardMapper.valueFromStringToInt(testJson));
    }

}


