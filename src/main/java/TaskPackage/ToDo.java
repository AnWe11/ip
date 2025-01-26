package TaskPackage;

public class ToDo extends TasksDefault {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        String taskType = "[T]";
        str.append(taskType).append(super.getDescription());
        return str.toString();
    }

}
