/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xml;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import xml.XMLParser;
/**
 *
 * @author LEDU0_000
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        XMLParser xmpParser = new XMLParser();
        ArrayList<ListNode> listOfNode= new ArrayList<ListNode>();
        ArrayList<String> listText = new ArrayList<String>();
     try
        {
	    FileInputStream in=new FileInputStream("test/SampleXML.xml");
            BufferedWriter out = new BufferedWriter(new FileWriter("test/NameBase.txt"));
	   listOfNode= xmpParser.parse(in);
           listText = xmpParser.getListNodeText();
           
            for (ListNode listNode : listOfNode){
                Node tempHead = listNode.getHead();
               // System.out.println(tempHead.tagName);
               // while(tempHead.getNext() != null){
                 // if(tempHead.tagName.equalsIgnoreCase("milestone"))
                      //System.out.println(tempHead.xmlid);
                // tempHead = tempHead.getNext();
               // }
            }
           int increasing = 0;
            for(String t : listText){
                  //System.out.println(t);
                  increasing++;
                  out.write(increasing + ".    ");
                   out.write(t);
                   out.write(System.getProperty("line.separator"));

                }
      in.close();
      out.close();
    }
    catch (IOException e) { System.out.println("IOError: "+e); }
  }
    

}
