export interface TicketTableSettings {
    selectedFilter: TicketTableFilter;
    filter: TicketTableFilter[];
    defaultCoulmns: string[];
}

export interface TicketTableStatusFilter {
    isCREATED: boolean;
    isWAITING: boolean;
    isREFINEMENT: boolean;
    isIN_PROGRESS: boolean;
    isDONE: boolean;
}

export interface TicketTableFilter {
    name: string;
    statusFilter: TicketTableStatusFilter;
    displayedColumns: string[];
}

export interface X {
    filterName: string;
    done: boolean;
    create: boolean;
    in_PROGRESS: boolean;
    refinement: boolean;
    waiting: boolean;
    displayedColumns: string[];
}