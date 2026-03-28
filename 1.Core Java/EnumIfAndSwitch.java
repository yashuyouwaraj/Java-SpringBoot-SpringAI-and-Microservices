enum Status{
    Running,
    Completed,
    Failed,
    In_PROGRESS
}

public class EnumIfAndSwitch {
    public static void main(String[] args) {
        Status s = Status.Completed;

        if(s==Status.Running){
            System.out.println("Running");
        } else if(s==Status.Completed){
            System.out.println("Completed");
        } else if(s==Status.Failed){
            System.out.println("Failed");
        } else if(s==Status.In_PROGRESS){
            System.out.println("In Progress");
        } else{
            System.out.println("Some Error");
        }

        switch (s) {
            case Running:
                System.out.println("Running");
                break;
            case Completed:
                System.out.println("Completed");
                break;
            case Failed:
                System.out.println("Failed");
                break;
            case In_PROGRESS:
                System.out.println("In Progress");
                break;
            default:
                System.out.println("Some Error");
                break;
        }
    }
}
