package ru.anastasia.test.chapter7;

public class MonsterTestDrive {
    public static void main(String[] args) {
        Monster [] ma = new Monster[3];
        ma[0] = new Monster.Vampire();
        ma[1] = new Monster.Vampire.Dragon();
        ma[2] = new Monster();
        for(int x = 0; x < 3; x++) {
            ma[x].frighten(x);
        }
    }
}

class Monster {
    boolean frighten(int d) {
        System.out.println("Грррррр");
        return true;
    }

    static class Vampire extends Monster {
        boolean frighten(int x) {
            System.out.println("Укусить?");
            return false;
        }

        static class Dragon extends Monster {
            boolean frighten(int degree) {
                System.out.println("Огненное дыхание");
                return true;
            }
        }
    }
}