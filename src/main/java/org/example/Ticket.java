package org.example;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.ValidationException;
import java.util.Objects;
import java.util.Set;

public class Ticket {
    private static int ticketIdCounter = 1;

    private int ticketId;
    private Concert concert;
    private Client client;

    public Ticket(Concert concert, Client client) {
        this.ticketId = ticketIdCounter++;
        this.concert = concert;
        this.client = client;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Concert getConcert() {
        return concert;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", concert=" + concert +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Objects.equals(concert, ticket.concert) && Objects.equals(client, ticket.client);
    }



    }


