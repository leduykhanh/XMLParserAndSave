/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xml;

/**
 *
 * @author LEDU0_000
 */
public class Node {
    public String tagName;
    Node sub;
    public String text;
    public String xmlid;
    public String ed;
    public String n;
    public  Node() {
        tagName = "";
        sub = null;
        text = "";
        xmlid = "";
        ed = "";
        n = "";
    }
    public  Node(String tagName,Node sub) {
        this.tagName = tagName;
        this.sub = sub;
        this.text = "";
        xmlid = "";
        ed = "";
        n = "";
    }
    public Node getNext(){
    return this.sub;
    }
    public void setNext(Node node){
    this.sub = node;
    }

}
