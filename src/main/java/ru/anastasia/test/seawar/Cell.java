package ru.anastasia.test.seawar;

public class Cell {
    // true - есть корабль
    // false - пусто
    // null - сюда уже стреляли
    private Boolean status;

    public Cell() {
        this.status = false;
    }

    public Cell(Boolean status) {
        this.status = status;
    }

    public void shoot() {
        this.status = null;
    }

    public Boolean getStatus() {
        return this.status;
    }
}
