// this is a cut down version of the class specifically for the Remove test
// the complete version is in the Library folder on Moodle

public class SortedLinkedList implements SortedADT {

    private class ListNode {

        private Comparable object;
        private ListNode next;
    }

    private ListNode head;
    // set by find to allow remove to remove the found node
    private ListNode current;
    private ListNode previous;

    public String toString() {
        /* algorithm
            set up a string to contain the list details
            if list not empty then
                set current node to head of list
                while nodes remain loop
                    add the node object to the string
                    move to next node
                end loop
            else
                add empty list message to the string
         end if
         */
        String listDetails = new String();
        if (this.head != null) {
            ListNode current = this.head;
            while (current != null) {
                listDetails += current.object + "\n";
                current = current.next;
            }
        } else {
            listDetails += "list is empty";
        }
        return listDetails;
    }

    public void insert(Comparable object) {
        // cut down version, for this test objects are added in order
        ListNode newNode = new ListNode();
        newNode.object = object;
        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode current = this.head;
            Boolean insertionPositionFound = false;
            while (!insertionPositionFound) {
                if (current.next == null) {
                    insertionPositionFound = true;
                    current.next = newNode;
                } else {
                    current = current.next;
                }
            }
        }
    }

    public Comparable find(Comparable object) throws NotFoundException {
        /* algorithm
            if list empty then
                throw not found exception
            end if
            set current node to head of list
            while object not found loop
                if object matches current node object then
                    object found
                else
                    if object is less than current object then
                        // object is not in the list
                        throw not found exception
                    else
                        if no more objects to compare with then
                            // object is not in the list
                            throw not found exception
                        else
                            // move to the next node
                            set the previous node to the current node
                            set the current node to the next node
                        end if
                    end if
                end if
            end loop
         */
        if (this.head == null) {
            throw new NotFoundException();
        }
        Comparable foundObject = null;
        this.current = this.head;
        while (foundObject == null) {
            if (object.compareTo(this.current.object) == 0) {
                foundObject = this.current.object;
            } else if (object.compareTo(this.current.object) < 0) {
                throw new NotFoundException();
            } else if (this.current.next == null) {
                throw new NotFoundException();
            } else {
                this.previous = this.current;
                this.current = this.current.next;
            }
        }
        return foundObject;
    }

    public Comparable remove(Comparable object) throws NotFoundException {
        /* algorithm
        find the object //sets up current and previous
        if current node is the head then
        link the head to the next node
        else
        link the previous node to the next node
        end if
        remove object and link from current node
         */
        // current will refer to the node to be removed
        // previous will refer to the node immediately before the one to be removed
        // add code here
        Comparable removedObject = find (object);
        if (current == this.head) {
            this.head = current.next;
        } else {
            previous.next = current.next;
        }
//        current.next = null;
        return removedObject;
    }
}
