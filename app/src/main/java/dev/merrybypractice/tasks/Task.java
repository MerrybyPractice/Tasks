package dev.merrybypractice.tasks;


public class Task {

    private String title;

    private String description;

    private boolean assigned;

    private boolean finished;

    public String getId() {
        return id;
    }

    private String id;

    public Task setID(String id) {
        this.id = id;
        return this;
    }

    public Task(String title, boolean assigned, boolean finished, String description) {
        this.title = title;
        this.assigned = assigned;
        this.finished = finished;
        this.description = description;
    }

    public Task() {
    }

    ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
