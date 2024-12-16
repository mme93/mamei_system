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
    rows: PixelQuestMapGridElementDto[][]
}

export interface PixelQuestMapGridElementDto {
    rowIndex: number,
    columnIndex: number,
    baseTexture: string,
    item: string,
    hasPerson?:boolean
}

export interface PixelQuestAccountDto {
    userName: string,
    currentWorldId: number,
    currentMapId: number,
    mapColIndex: number,
    mapRowIndex: number

}

export interface PixelQuestComponentView{
    isWorld:boolean,
    isLogin:boolean
}