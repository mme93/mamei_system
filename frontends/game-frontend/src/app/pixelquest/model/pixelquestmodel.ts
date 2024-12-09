export interface PixelQuestContainer{
    colSize:number;
    rowSize:number;
    pixelQuestGrid:PixelQuestGrid;
    settings:PixelQuestSettings;
}

export interface PixelQuestSettings{
    offsetX: number; 
    offsetY: number; 
    isDragging: boolean;
    lastMouseX: number;
    lastMouseY: number;
}
export interface PixelQuestRow{
    pixelElements:PixelQuestGridElement[];
}

export interface PixelQuestGridElement{
    colIndex:number;
    rowIndex:number;
    image:string;
    background?: string;
}

export interface PixelQuestGrid{
    pixelRow:PixelQuestRow[];
}