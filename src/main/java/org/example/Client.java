package org.example;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Client {
    private static int clientIdCounter = 1;

    private int clientId;
    private String name;
    private List<Ticket> tickets;

    public Client(String name) {
        this.clientId = clientIdCounter++;
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void buyTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId && Objects.equals(name, client.name) && Objects.equals(tickets, client.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, tickets);
    }

    public static class ClientBuilder{
        private static int clientIdCounter = 1;
        private int clientId;

        @NotNull(message = "Must be not null")
        private String name;


        private List<Ticket> tickets;


        private List<Concert> concerts;

        public Client.ClientBuilder id(){
            this.clientId = clientIdCounter++;
            return this;
        }

        public Client.ClientBuilder name(String name){
            this.name = name;
            return this;
        }


        public Client.ClientBuilder ticket(){
            this.tickets = new ArrayList<>();
            return this;
        }

        public Client build() throws ValidationException {
            Client client = new Client(name);

            return client;
        }

        public Client validateTicket(Client client){
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
            String fieldName = "";
            for (ConstraintViolation constraintViolation : constraintViolations) {
                fieldName += constraintViolation.getPropertyPath().toString().toUpperCase();
            }

            if(fieldName.length()>0) throw  new javax.validation.ValidationException();

            return client;
        }

    }
}