export interface GameInterface {

}

export interface EditorButtonAction {
    buttonTyp: string;
    imageType: string;
}

export interface NewMap {
    images: NewMapImagePaths;
    settings: NewMapSettings;
    grid: NewMapGridRow[];
}

export interface NewMapSettings {
    title: string;
    rows: number;
    cols: number;
    blockWidth: number;
    blockHight: number;
}

export interface NewMapGridElement {
    field_image: string;
    field_object_image: string;
}

export interface NewMapGridRow {
    elements: NewMapGridElement[];
}

export interface NewMapImagePaths {
    imageType: string;
    fields: string;
    objects: string;
}

export interface NewMapImage {
    title: string;
    src: string;
    category: String;
    isSelected: boolean;
    size: string;
    objectSize?: string;
}