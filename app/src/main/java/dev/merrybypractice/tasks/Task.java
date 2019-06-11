package dev.merrybypractice.tasks;


public class Task {

    private String title;

    public String getDescription() {
        return description;
    }

    private String description;

    private boolean assigned = false;

    private boolean finished = false;

    public String getId() {
        return id;
    }

    private String id;

    public Task setID(String id) {
        this.id = id;
        return this;
    }

    public Task(String title, boolean assigned, boolean finished, String description, String id) {
        this.title = title;
        this.assigned = assigned;
        this.finished = finished;
        this.description = description;
        setID(id);
    }

    public Task() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }


    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean getAssigned() {

        return assigned;
    }

    public boolean getFinished() {
        return finished;
    }

}
