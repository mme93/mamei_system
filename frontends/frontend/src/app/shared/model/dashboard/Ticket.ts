export interface CreateTicket {
    title: string,
    description: string,
    startDate: Date,
    endDate: Date,
    createDate:Date,
    deadLine: boolean
    type: string,
    label: string,
    classification: string
}

export interface Ticket {
    id?: number;
    title: string,
    description: string,
    startDate: Date,
    endDate: Date,
    createDate: Date,
    deadLine: boolean,
    type: string,
    label: string,
    classification: string,
    status: string
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