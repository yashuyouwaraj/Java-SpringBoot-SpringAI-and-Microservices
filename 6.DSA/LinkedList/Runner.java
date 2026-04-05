

public class Runner {

    public static void main(String[] args) {
        // // Create an empty list
        // LinkedList<String> l = new LinkedList<>();

        // // Use add() to add 
        // // elements in the list
        // l.add("Geeks");
        // l.add("For");
        // l.add("Geeks");

        // // Creating a ListIterator
        // ListIterator<String> it = l.listIterator();

        // // Using ListIterator to traverse the list
        // // hasNext() is used to check 
        // // if there are more elements in the list
        // while (it.hasNext()) {

        //     // next() is used to retrieve
        //     // elements in the list
        //     System.out.print(it.next() + " ");
        // }

        LinkedList list = new LinkedList();
        list.insert(5);
        list.insert(15);
        list.insert(20);
        list.insertAtStart(25);
        list.insertAt(2, 30);
        list.deleteAt(2);

        list.show();

        
    }
}
