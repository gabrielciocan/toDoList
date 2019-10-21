package org.fasttrackit.todolist.transfer;

import java.time.LocalDate;

public class UpdateToDoItemRequest {

    private String description;
    private LocalDate deadline;
    private boolean done;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "UpdateToDoItemRequest{" +
                "description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }w
}
