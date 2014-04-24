package xml;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import xml.Node;


public class XMLParser
{
  ArrayList<String> listOfNodeText = new ArrayList<String> ();
  public ArrayList<String> getListNodeText(){
    return listOfNodeText;
  }
  public  ArrayList<ListNode> parse(FileInputStream file)
  {
    String temp;
    ArrayList<ListNode> listOfListNode= new ArrayList<ListNode>();
    boolean isOuterNode = true;
    boolean isNameColsing = false;
    ListNode listOfNode = new ListNode(null);
    Node outerNode = new Node();
    Node currentNode = new Node();
    String debugger = "";
    String currentKey = "";
    try
    {
      char val=(char) file.read();
      BufferedWriter out = new BufferedWriter(new FileWriter("test/NewSample.xml"));

      StringBuffer text= new StringBuffer(50);
      while (val!=(char) -1)
      {
         debugger += val;
         out.write(val);
        if (val=='<')
        {
          boolean inquotes=false;
          StringBuffer name=new StringBuffer(50);

          do  // read the name of the XML node
          {
                val=(char) file.read();

                if (val=='\"')
                  inquotes^=true;
                else if (inquotes || (val!='>' && val!=' '))
                  name.append(val);
          } while (val!=(char) -1 && ((val!=' ' && val!='>') || inquotes));
          
          temp=name.toString();
          out.write(temp);
          out.write(val);
          if (temp.endsWith("/")) // self closing tag
          {
                currentNode = new Node(temp,null);

          }
          else if (name.charAt(0)=='/')
                    {
                    //System.out.println("closing tag: "+temp);
                    //out.write(temp);
                    if (temp.substring(1).equalsIgnoreCase(outerNode.tagName))
                         isOuterNode = true;
                    if(currentNode.tagName.equalsIgnoreCase("name")
                            && temp.substring(1).equalsIgnoreCase("name"))
                    {
                        boolean existing = false;
                        for(String tx : listOfNodeText)
                            if(text.toString().equalsIgnoreCase(tx))
                                existing = true;
                        if(!existing)
                        listOfNodeText.add(text.toString());
                        // System.out.println("closing tag: "+temp);
                         //System.out.println("adding "+text);
                         
                        }
                    text= new StringBuffer(250);
                   // System.out.println(isOuterNode);
                }
          else
          {
            //System.out.println("Opening tag: "+temp);
            text= new StringBuffer(250);
            if(isOuterNode) {
                outerNode = new Node(temp,null);
                currentNode = new Node(temp,null);
                listOfNode = new ListNode(outerNode);
                listOfListNode.add(listOfNode);
                isOuterNode = false;
                  }
            else {
            
            Node subNode = new Node(temp,null);
            currentNode = new Node(temp,null);
            listOfNode.add(currentNode);
                  }
           

            // the '>' character indicates that all key-value pairs have been read
            
            while (val!=(char) -1 && val!='>') // read its properties
            {
              // key="value"
              StringBuffer key=new StringBuffer(50);
              do // read in a key
              {
                val=(char) file.read();
                if(currentNode.tagName.equalsIgnoreCase("milestone")
                        && !currentNode.n.equalsIgnoreCase("")&&(val!='>'||val!='/'))
                    out.write("");
                else
                    out.write(val);
                if (val=='\"')
                  inquotes^=true;
                else if ((val!='=' && val!=' ') || inquotes)
                  key.append(val);
              } while (val!=(char) -1 && ((val!='=' && val!=' ' && val!='>') || inquotes));

              String tmp=key.toString();
                if(currentNode.tagName.equalsIgnoreCase("milestone"))
                {
                    //System.out.println(key);
                    if(currentKey.equalsIgnoreCase("n"))
                        currentNode.n = key.toString();
                    else if (currentKey.equalsIgnoreCase("ed"))
                        currentNode.ed = key.toString();
                    else if (currentKey.equalsIgnoreCase("unit"))
                    {
                        currentNode.xmlid = "BookI-Translation_" + currentNode.ed + "_" + currentNode.n;
                        out.write("n=\""+currentNode.n+"\" "+"ed=\""+currentNode.ed+"\" "+" xml:id = \""+currentNode.xmlid+"\" />");
                    }

                }
              currentKey = key.toString();
              if (tmp.endsWith(">"))
              {
                if (tmp.endsWith("/>") || tmp.endsWith("/ >"))
                {
                 // System.out.println("closing tag");
                    if (tmp.equalsIgnoreCase(outerNode.tagName))
                            isOuterNode = true;
                    //System.out.println(isOuterNode);
                }
              }
              else
              {
                // Read in a value for the current key
                // A value ends with a " ", ">" or "/" outside of quotes
                // if "/" is the terminating value then you also want to close this 
                // tag as it is a self-closing one
                // e.g. <cb n="5"/>

                // ... need to read in the value here

                //System.out.println(key+"="+value);
                if (val=='/')
                {
                 // System.out.println("closing tag");
                  val=(char) file.read();
                }
              }
            }
          }
        }
        else
        {
         // System.out.println("Normal character: "+val);
             if(currentNode.tagName.equalsIgnoreCase("name"))
            {
                text.append(val);
                 
            }

        }

        val=(char) file.read();
      }
      out.close();
    }
    catch (Exception e) {System.out.println("XMLParser.parse() - "+e);}
    return listOfListNode;
  }
}