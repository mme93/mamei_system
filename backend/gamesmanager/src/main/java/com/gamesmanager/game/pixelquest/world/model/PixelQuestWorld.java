package com.gamesmanager.game.pixelquest.world.model;

import java.util.ArrayList;
import java.util.List;

public class PixelQuestWorld {

    private List<List<PixelQuestWorldElement>> worldRows;

    public PixelQuestWorld() {
        this.worldRows = new ArrayList<>();
    }

    public void addRows(List<PixelQuestWorldElement> elements){
        worldRows.add(new ArrayList<>(elements));
    }

    public void addEmptyRow(){
        worldRows.add(new ArrayList<>());
    }


    public void addElementToRow(PixelQuestWorldElement element, int index) {
        List<PixelQuestWorldElement>elements=worldRows.get(index);
        elements.add(element);
    }
}
