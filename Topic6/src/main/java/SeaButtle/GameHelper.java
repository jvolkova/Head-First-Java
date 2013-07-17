package SeaButtle;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        // Хранит координаты типа f6
        String[] alphaCoords = new String[comSize];
        // Временная строка для конкатанации
        String temp = null;
        // Координаты текущего сайта
        int[] coords = new int[comSize];
        // Счетчик текущих попыток
        int attempts = 0;
        // Нашли подходящее местоположение?
        boolean success = false;
        // Текущее начальное местоположение
        int location = 0;

        // Энный сайт для размещения
        comCount++;
        // Устанавливаем горизонтальный инкремент
        int incr = 1;
        // Если нечетный - размещаем вертикально
        if ((comCount % 2) == 1) {
            // Устанавливаем вертикальный инкремент
            incr = gridLength;
        }

        // Главный поисковый цикл (32)
        while ( !success & attempts++ < 200) {
            // Получаем случайную стартовую точку
            location = (int) (Math.random() * gridSize);
            //System.out.print("пробуем" + location);
            // Энная позиция в сайте, который нужно разместить
            int x = 0;
            success = true;
            while (success && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else {
                    // System.out.print("Используется" + location);
                    success = false;
                }
            }

        }

        int x = 0;
        int row = 0;
        int column = 0;
        //System.out.println("\n");
        while (x < comSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            // System.out.print(" coord" + x + " = " + alphaCells.get(x-1));
        }
        return alphaCells;
    }
}
