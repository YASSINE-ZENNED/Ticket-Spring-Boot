package com.ticket.events;

public record UpdatSeatsRequest(Integer numberOfSeats) {
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
