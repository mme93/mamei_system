export interface GameInterface{
    
}

export interface NewMap{
    settings:NewMapSettings;
    grid:NewMapGridRow[];
}

export interface NewMapSettings{
    title:string;
    rows:number;
    cols:number;
    blockWidth:number;
    blockHight:number;
}

export interface NewMapGridElement{
    field_image:string;
    field_object_image:string;
}

export interface NewMapGridRow{
    elements:NewMapGridElement[];
}