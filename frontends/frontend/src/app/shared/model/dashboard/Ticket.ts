export interface CreateTicket {
    title: string,
    ticketType: string,
    ticketPrios: string,
    description: string,
    startDate: Date,
    endDate: Date,
    deadLine: boolean
}

export interface Ticket {
    id?: number;
    title: string,
    ticketType: string,
    ticketPrios: string,
    description: string,
    startDate: Date,
    endDate: Date,
    deadLine: boolean
}

export interface TicketDropDown {
    value: string;
    viewValue: string;
}

export interface CreateTicketContent {
    ticketTypes: TicketDropDown[],
    ticketLabel: TicketDropDown[],
    ticketPrios: TicketDropDown[],
}