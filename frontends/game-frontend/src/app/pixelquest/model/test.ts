export interface PixelQuestWorldDto {
    name: string,
    maps: PixelQuestMapDto[]
}

export interface PixelQuestMapDto {
    height: number,
    width: number,
    pixelQuestMap: string,
    grid: PixelQuestGridDto
}

export interface PixelQuestGridDto {
    rows:PixelQuestMapGridElementDto[][]
}

export interface PixelQuestMapGridElementDto {
    rowIndex: number,
    columnIndex: number,
    baseTexture: string,
    item: string
}