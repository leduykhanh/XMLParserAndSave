/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xml;

/**
 *
 * @author LEDU0_000
 */
public class ListNode {
Node head;
Node tail;
public int numOfItems = 0;
public ListNode(){
        this.head =this.tail = null;
        numOfItems = 0;
}

public ListNode(Node node){
        this.head =this.tail = node;
        numOfItems = 1;
}
public ListNode(Node head,Node tail){
        this.head = head;
        this.tail = tail;
        numOfItems = 1;
}
public void add(Node node){
        tail.setNext(node);
        tail = tail.getNext();
        numOfItems ++;
}
public Node getHead(){
         return head;
}
}
