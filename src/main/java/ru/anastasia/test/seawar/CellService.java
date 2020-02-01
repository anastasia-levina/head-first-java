package ru.anastasia.test.seawar;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CellService {
    private Cell[][] playField = new Cell[10][10];
    private List<Character> validCharacters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
    private static int MAX_FIELD_SIZE = 10;
    private static int MAX_SHIP_COUNT = 5;
    private static int MAX_SIZE = 5;

    /**
     * Проверяет стрелял ли сюда игрок
     * @param ch буква. Должна быть из списка validCharacters
     * @param y число. Должно быть от 1 до 10
     */
    public boolean isVirgin(char ch, int y) {
        int x = validCharacters.indexOf(ch);
        return playField[x][y].getStatus();
    }

    /**
     * Проверяет валидны ли введенные пользователем координаты
     * @param ch буква. Должна быть из списка validCharacters
     * @param num число. Должно быть от 1 до 10
     */
    public boolean isValid(char ch, int num) {
        return validCharacters.contains(ch) && num > 0 && num <= 10;
    }

    /**
     * Стреляет по заданным координатам. Выстрел изменяет статус объекта Cell с false или true на null.
     * Возвращает true, если там был корабль. false, если мимо
     */
    public boolean attack(char ch, int num) {
        Boolean result = playField[validCharacters.indexOf(ch)][num].getStatus();
        playField[validCharacters.indexOf(ch)][num].shoot();
        return result;
    }

    /**
     * Наполняет игровое поле пустыми значениями
     */
    public void createPlayField() {
        for (int i = 0; i < MAX_FIELD_SIZE; i++) {
            for (int x = 0; x < MAX_FIELD_SIZE; x++) {
                playField[i][x] = new Cell();
            }
        }
    }

    /**
     * Расставляет корабли. По одному каждого типа
     */
    public void setShips() {
        while (MAX_SHIP_COUNT != 0) {
            setShip(MAX_SIZE);
            MAX_SIZE--;
            MAX_SHIP_COUNT--;
        }
    }

    /**
     *
     */
    private boolean isEmpty(int[] coord) {
        return playField[coord[0]][coord[1]].getStatus();
    }

    /**
     *
     */
    private boolean isEmpty(int[] start, int end[]) {
        if (end[0] != start[0]) {
            while (end[0] != start[0]) {
                if (!isEmpty(start)) {
                    return false;
                }
                start[0]++;
            }
        } else {
            while (end[1] != start[1]) {
                if (!isEmpty(start)) {
                    return false;
                }
                start[1]++;
            }
        }
        return true;
    }

    /**
     * Случайным образом располагает на поле корабль заданного размера
     * @param size размер корабля
     */
    private void setShip(int size) {
        //Случайное число. От того четное оно или нет зависит будет корабль расположен по оси x или y
        int axle = ThreadLocalRandom.current().nextInt();
        int randomX;
        int randomY;
        boolean success = false;
        int[][] ship;
        //будет пытаться расположить корабль пока не удастся подобрать подходящие координаты
        while (!success) {
            //располагаем по оси Y
            if (axle % 2 == 0) {
                randomX = ThreadLocalRandom.current().nextInt(0, MAX_FIELD_SIZE + 1);
                randomY = ThreadLocalRandom.current().nextInt(0, MAX_FIELD_SIZE + 1 - MAX_SIZE);
            //располагаем по оси X
            } else {
                randomX = ThreadLocalRandom.current().nextInt(0, MAX_FIELD_SIZE + 1 - MAX_SIZE);
                randomY = ThreadLocalRandom.current().nextInt(0, MAX_FIELD_SIZE + 1);
            }
            //проверяем пусты ли все клетки, необходимые для размещения корабля
            success = isEmpty(getShipStart(randomX, randomY), getShipEnd(randomX, randomY, MAX_SIZE, axle));
            if (success) {
                while (size != 0) {
                    playField[randomX][randomY] = new Cell(true);
                    if (axle % 2 == 0) {
                        randomY ++;
                    } else {
                        randomX++;
                    }
                    size--;
                }
                //заканчиваем работу метода
                break;
            }
        }

    }

    /**
     * Возвращает координаты начала корабля
     */
    private int[] getShipStart(int x, int y) {
        return new int[]{x, y};
    }

    /**
     * Возвращает координты конца корабля
     * @param x координаты по оси Х
     * @param y координаты по оси У
     * @param size размер корабля
     * @param axle если четное то строит воображаемый корабль по оси У, если нет - то по оси Х
     * @return координаты по осям ХУ
     */
    private int[] getShipEnd(int x, int y, int size, int axle) {
        if (axle % 2 == 0) {
            return new int[]{x, y + size};
        }
        return new int[]{x + size, y};
    }
}
