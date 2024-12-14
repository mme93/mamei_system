package com.gamesmanager.game.pixelquest.world.model;

public class PixelQuestWorldElement {
    private int rowSize;
    private int colSize;
    private int rowIndex;
    private int colIndex;
    private String backgroundImg;
    private boolean hasPerson;

    public PixelQuestWorldElement(int rowSize, int colSize, int rowIndex, int colIndex, String backgroundImg, boolean hasPerson) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.backgroundImg = backgroundImg;
        this.hasPerson = hasPerson;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public boolean isHasPerson() {
        return hasPerson;
    }

    public void setHasPerson(boolean hasPerson) {
        this.hasPerson = hasPerson;
    }
}
