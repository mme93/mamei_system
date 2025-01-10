export interface CreateTicket{
          title: string,
          ticketType: string,
          ticketPrios: string,
          description: string,
          startDate: Date,
          endDate: Date,
          deadLine: boolean
}

export interface Ticket{
    id?:number;
    title: string,
    ticketType: string,
    ticketPrios: string,
    description: string,
    startDate: Date,
    endDate: Date,
    deadLine: boolean
}