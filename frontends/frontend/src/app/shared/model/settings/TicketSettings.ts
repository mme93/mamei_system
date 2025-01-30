export interface TicketTableFilter {
    filterName: string;
    done: boolean;
    created: boolean;
    in_PROGRESS: boolean;
    refinement: boolean;
    waiting: boolean;
    displayedColumns: string[];
}