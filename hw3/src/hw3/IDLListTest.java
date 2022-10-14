package hw3;

/**
 * This class implements a doubly linked list of Nodes.
 * 
 * @author Peilin Feng
 * @date 10/10/2022
 */

public class IDLListTest {

    public static void main(String[] args){
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        System.out.println(list); // [1, 2, 3, 4]
        list.removeAt(2);
        System.out.println(list); // [1, 2, 4]
        list.append(5);
        System.out.println(list); // [1, 2, 4, 5]
        list.add(6); 
        System.out.println(list); // [6, 1, 2, 4, 5]
        System.out.println(list.size()); // 5
        System.out.println(list.getHead()); // 6
        System.out.println(list.getLast()); // 5
        System.out.println(list.remove()); // 6
        System.out.println(list.removeLast()); // 5
        System.out.println(list); // [1, 2, 4]
        System.out.println(list.remove(4)); // true
        System.out.println(list); // [1, 2]
        System.out.println(list.remove(4)); // false
        System.out.println(list); // [1, 2]
        System.out.println(list.get(0)); // 1
        System.out.println(list.getHead()); // 1
        System.out.println(list.getLast()); // 2
    }

}
