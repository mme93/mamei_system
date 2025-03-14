export interface Player {
    playerX: number;
    playerY: number;
    image: HTMLImageElement;
}

export interface GridContent {
    totalWidth: number;
    totalHeight: number;
    visibleWidth: number;
    visibleHeight: number;
    tileSize: number;
    offsetX: number;
    offsetY: number;
    visibleStartX: number;
    visibleStartY: number;
}

export interface CanvasGame {
    moved: boolean;
    player: Player;
    gridContent: GridContent;
}

export interface CanvasGameField {
    path: string;
}
