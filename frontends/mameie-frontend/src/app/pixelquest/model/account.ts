export interface PixelQuestAccountDto {
    userName?: string,
    currentWorldId?: number,
    currentMapId?: number,
    mapColIndex?: number,
    mapRowIndex?: number
    pixelQuestMap?: string;
}

export interface PixelQuestUserDto {
    userName: string;
    password: string;
}