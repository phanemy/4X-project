package utilities;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exception.XmlException;

public class XmlReader {
	
	public static void readXml()
	{
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();
		    final Document document= builder.parse(new File("resources/unit.xml"));

		    final Element racine = document.getDocumentElement();
		    System.out.println(racine.getNodeName());
		   /* final NodeList racineNoeuds = racine.getChildNodes();
		    final int nbRacineNoeuds = racineNoeuds.getLength();

		    
		    for (int i = 0; i<nbRacineNoeuds; i++) {
		        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            final Element personne = (Element) racineNoeuds.item(i);
		            
		            System.out.println(personne.getNodeName());
		        	System.out.println("sexe : " + personne.getAttribute("sexe"));
		            final Element nom = (Element) personne.getElementsByTagName("nom").item(0);
		            System.out.println(nom.getTextContent());
		            final NodeList telephones = personne.getElementsByTagName("telephone");
		            final int nbTelephonesElements = telephones.getLength();
		                                
		            for(int j = 0; j<nbTelephonesElements; j++) {
		                final Element telephone = (Element) telephones.item(j);
		                System.out.println(telephone.getAttribute("type") + " : " + telephone.getTextContent());
		            }
		        }				
		    }*/
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}
	}

	private String afficheXml(Element racine,int offset) {
		String temp="";
		final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
	    //System.out.println(nbRacineNoeuds);
	    if(nbRacineNoeuds == 1)
	    {	
	    	for(int i=0;i<offset;i++)
	    	{
	    		temp+="\t";
	    	}
	    	temp+="<"+racine.getNodeName()+">";
	    	temp+=racine.getTextContent();
	    	temp+="</"+racine.getNodeName()+">\n";
	    	return temp;
	    }
	    else
	    {	
	    	for(int i=0;i<offset;i++)
	    	{
	    		temp+="\t";
	    	}
	    	temp+="<"+racine.getNodeName()+">\n";
	    	
	    	for (int i = 0; i<nbRacineNoeuds; i++) {
	    	     if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            final Element personne = (Element) racineNoeuds.item(i);
		            temp+=afficheXml(personne,offset+1);
	    	     }
	    	}
	    	for(int i=0;i<offset;i++)
	    	{
	    		temp+="\t";
	    	}
	    	temp+="</"+racine.getNodeName()+">\n";
	    	return temp;
	    }
	}
	
	private String afficheXmlPropre(Element racine,int offset) {
		String temp="";
		final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
	    //System.out.println(nbRacineNoeuds);
	    if(nbRacineNoeuds == 1)
	    {	
	    	for(int i=0;i<offset;i++)
	    	{
	    		temp+="  ";
	    	}
	    	temp+=racine.getNodeName()+": "+racine.getTextContent()+"\n";
	    	return temp;
	    }
	    else
	    {	
	    	for(int i=0;i<offset;i++)
	    	{
	    		temp+="  ";
	    	}
	    	temp+=racine.getNodeName()+"\n";
	    	
	    	for (int i = 0; i<nbRacineNoeuds; i++) {
	    	     if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            final Element personne = (Element) racineNoeuds.item(i);
		            temp+=afficheXmlPropre(personne,offset+1);
	    	     }
	    	}
	    	return temp+"\n";
	    }
	}
	
	//launch the recursive method 
	public static String findAtributeForUnitId(int idSearch, String attributeSearch)
			throws XmlException
	{
		String result = new String("");
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();
		    final Document document= builder.parse(new File("resources/unit.xml"));

		    Element racine = document.getDocumentElement();
		    NodeList racineNoeuds = racine.getChildNodes();
		    Element temp = null;
		    final int nbRacineNoeuds = racineNoeuds.getLength();
		    int i =0;
		    boolean notFind = true;
		    while(i < racineNoeuds.getLength() && notFind)
		    {
			    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
			    {
				 
				   temp = (Element) racineNoeuds.item(i);
				   //System.out.println("a "+(temp.getNodeName()=="unit") +" " + (temp.getAttribute("id").equals(String.valueOf(idSearch))) );
				   if(temp.getNodeName().equals("unit") && temp.getAttribute("id").equals(String.valueOf(idSearch)))
				   {
					   notFind=false;
				   }
				   else
				   {
					   i++;
				   }
			    }
			    else
			    {
			    	i++;
			    }
		   }
		   if(i==racineNoeuds.getLength())
		   {
				   throw new XmlException("le numero d'id " + idSearch +" ou l'atribut "
		    			+ attributeSearch +" est introuvable");
		   }
		   else
		   {
			   racine = (Element) racineNoeuds.item(i);
			   racineNoeuds = racine.getChildNodes();
			   i=0;
			   notFind=true;
			   Element attribute = (Element) racine.getElementsByTagName(attributeSearch).item(0);
			   /*while(i < racineNoeuds.getLength()
				   		&& notFind)
			   {
				   if(racineNoeuds.item(i).getNodeType()==Node.ELEMENT_NODE)
				   {
					    temp = (Element) racineNoeuds.item(i);
					    
					   if(temp.getNodeName().equals(String.valueOf(attributeSearch)))
					   {
						   notFind=false;
					   }
					   else
					   {
						   i++;
					   }
				   }
				   else
				   {
					   i++;
				   }
			   result=temp.getTextContent();
			  }*/
			   result = attribute.getTextContent();
		   }
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}
		
		return result;
	}
	
	//the recursive method 
	private static String findAtributeForUnitId(int idSearch, String attributeSearch,Element racine)
	{
		String result = new String("");
		final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
	    //System.out.println(nbRacineNoeuds);
	    if(nbRacineNoeuds==1)
	    {
	    	return result;
	    }
	    else if(racine.getNodeName()=="unit")
	    {	
	    	int i = 0;
	    	while(i<racineNoeuds.getLength())
	    	{
		    	if(racineNoeuds.item(i).getNodeType()==Node.ELEMENT_NODE
		    			&& racineNoeuds.item(i).getNodeName() ==attributeSearch)
		    	{
		    		result += racineNoeuds.item(i).getTextContent();	
		    		i = racineNoeuds.getLength();
		     	}
		    	i++;
	    	}
	    	return result;
	    	
	    }
	    else
	    {
	    	for (int i = 0; i<nbRacineNoeuds; i++) {
	    	     if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            final Element el = (Element) racineNoeuds.item(i);
		            result+=findAtributeForUnitId(idSearch, attributeSearch, el);
	    	     }
	    	}
	    	return result;
	    }
	}
}
