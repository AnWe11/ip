public class ToDo extends Tasks{

    private String taskType = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        str.append(this.taskType).append(super.getDescription());
        return str.toString();
    }

}
