//import VisualAdvancedBoggle.BoggleDictionaryReader;
//import VisualAdvancedBoggle.*;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class BoggleTests {
//
//    //BoggleGame  Test
//    @Test
//    void findAllWords_small() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        BoggleController game = new BoggleController();
//        Method method = game.getClass().getDeclaredMethod("findAllWords", Map.class, BoggleDictionaryReader.class, BoggleBoard.class);
//        method.setAccessible(true);
//
//        BoggleDictionaryReader boggleDict = new BoggleDictionaryReader("Ace/DictionaryWords.txt");
//        Map<String, ArrayList<Position>> allWords = new HashMap<>();
//        BoggleBoard grid = new BoggleBoard(4);
//        grid.initalizeBoard("RHLDNHTGIPHSNMJO");
//        Object r = method.invoke(game, allWords, boggleDict, grid);
//
//        Set<String> expected = new HashSet<>(Arrays.asList("GHOST", "HOST", "THIN"));
//        assertEquals(expected, allWords.keySet());
//    }
//
//    //Dictionary Test
//    @Test
//    void containsWord() {
//        BoggleDictionaryReader dict = new BoggleDictionaryReader("VisualAdvancedBoggle/DictionaryWords.txt");
//        assertTrue(dict.hasWord("ENZYME"));
//        assertTrue(dict.startswith("pench"));
//    }
//
//    //BoggleGrid Test
//    @Test
//    void setupBoard() {
//        BoggleBoard grid = new BoggleBoard(10);
//        String letters = "";
//        for (int i = 0; i < 10; i++) {
//            letters = letters + "0123456789";
//        }
//
//        grid.initalizeBoard(letters);
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                assertEquals(letters.charAt(i*10+j), grid.getCharAt(i, j));
//            }
//        }
//    }
//
//    //BoggleStats Test
//    @Test
//    void endRoundTest() {
//        BoggleScoreBoard stats = new BoggleScoreBoard();
//        stats.endRound();
//        stats.endRound();
//        stats.endRound();
//        assertEquals(3, stats.getRound());
//    }
//
//}
