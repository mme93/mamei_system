package com.gamesmanager.game.pixelquest.map.model;

import java.util.ArrayList;
import java.util.List;

public class PixelQuestMapGrid {

    private List<List<PixelQuestMapGridElement>> worldRows;

    public PixelQuestMapGrid() {
        this.worldRows = new ArrayList<>();
    }

    public void addRows(List<PixelQuestMapGridElement> elements){
        worldRows.add(new ArrayList<>(elements));
    }

    public void addEmptyRow(){
        worldRows.add(new ArrayList<>());
    }


    public void addElementToRow(PixelQuestMapGridElement element, int index) {
        List<PixelQuestMapGridElement>elements=worldRows.get(index);
        elements.add(element);
    }

}
