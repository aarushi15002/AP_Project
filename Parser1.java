import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;


public class Parser1 extends DefaultHandler{

		@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("hgf");
		super.startDocument();
	}

		protected static ArrayList<PublicationAuthor> List = new ArrayList<PublicationAuthor>();
		private String tmpValue;
		private PublicationAuthor recTmp;
		private Personrecords recTmp2;
//		ArrayList<ArrayList> listdd = new ArrayList<ArrayList>();
		int flag=0;
		int bigflag=0;
		
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
    	if (qName.equalsIgnoreCase("article") | qName.equalsIgnoreCase("inproceedings") | qName.equalsIgnoreCase("book") | qName.equalsIgnoreCase("incollection") | qName.equalsIgnoreCase("phdthesis") | qName.equalsIgnoreCase("mastersthesis") ) 
    	{
    		bigflag=1; //publication record
        	recTmp=new PublicationAuthor();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if(qName.equalsIgnoreCase("author")){
	           recTmp.addAuthor(tmpValue);
	           
	           
	    }
    	else if (qName.equalsIgnoreCase("article")| qName.equalsIgnoreCase("inproceedings") | qName.equalsIgnoreCase("book") | qName.equalsIgnoreCase("incollection") | qName.equalsIgnoreCase("phdthesis") | qName.equalsIgnoreCase("mastersthesis") ) {
           // List.add(recTmp);
//            if (flag==1)
//            {
//            recTmp.Toprint();
//            flag=0;
//            }
    		if(DBLP.q1==1 && DBLP.qsort1==1) // through author name since given yr
    		{
    			int k;
                for(k=0;k<DBLP.e.size();k++)
                {
                	String f = DBLP.e.get(k);
                	if(recTmp.getAuthor().contains(f) && recTmp.getYear()>=DBLP.since_somegivenyr)
                	{
                		//recTmp.Toprint();
                		List.add(recTmp);
                	}
                }
    		}
    		
    		else if(DBLP.q1==1 && DBLP.qsort1==2) // through author name between two yrs
    		{
    			int k;
                for(k=0;k<DBLP.e.size();k++)
                {
                	String f = DBLP.e.get(k);
                	if(recTmp.getAuthor().contains(f) && recTmp.getYear()>=DBLP.startyr && recTmp.getYear()<=DBLP.endyr)
                	{
                		//recTmp.Toprint();
                		List.add(recTmp);
                	}
                }
    		}
    		else if(DBLP.q1==2 && flag==1)
    		{
    			// since given yr
    			
    			if(DBLP.qsort2==1)
    			{
    				if(recTmp.getYear()>=DBLP.since_somegivenyr)
    				{
    					//recTmp.Toprint();
    					List.add(recTmp);
    	    			 
    				}
    			}
    			else if(DBLP.qsort2==2)
    			{
    				if(recTmp.getYear()>=DBLP.startyr && recTmp.getYear()<=DBLP.endyr )
    				{
    					//recTmp.Toprint();
    					List.add(recTmp);
    	    			 
    				}
    			}
    			flag=0;
    			 
    		}
    		else if(DBLP.q1==2 && flag==2)
    		{
    			if(DBLP.qsort2==3)
    			{
    				if(recTmp.getYear()>=DBLP.since_somegivenyr)
    				{
    					//recTmp.Toprint();
    					List.add(recTmp);
    	    			 
    				}
    			}
    			if(DBLP.qsort2==4)
    			{
    				if(recTmp.getYear()>=DBLP.startyr && recTmp.getYear()<=DBLP.endyr )
    				{
    					//recTmp.Toprint();
    					List.add(recTmp);
    	    			 
    				}
    				
    			}
    			flag=0;
    		}
            
            
            
            
        }
    	else if (qName.equalsIgnoreCase("journal")) {
            recTmp.setJournal(tmpValue);
        }
    	else if (qName.equalsIgnoreCase("booktitle")) {
            recTmp.setBookTitle(tmpValue);
        }
    	else if (qName.equalsIgnoreCase("volume")) {
            recTmp.setVolume(tmpValue);
        }
    	else if (qName.equalsIgnoreCase("pages")) {
            recTmp.setPages(tmpValue);
        }
    	else if (qName.equalsIgnoreCase("title")) {
            recTmp.setTitle(tmpValue);
            if(DBLP.q1==2 && DBLP.ch3.equals(tmpValue)&& DBLP.relevance==0)
            {
            	flag=1;
            }
            if(DBLP.q1==2 &&  DBLP.relevance==1)
            {
            	Iterator h = DBLP.wordArrayList.iterator();
            	while(h.hasNext())
            	{
            		String he = (String)h.next();
            	if(tmpValue.contains(he))
            	{
            		flag=2;
            	}
            	}
            	//flag=2;
            }
        }
    	else if (qName.equalsIgnoreCase("year")) {
            recTmp.setYear(Integer.parseInt(tmpValue));
        }
    	else if (qName.equalsIgnoreCase("url")) {
            recTmp.setUrl(tmpValue);
        }
    	
       
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	tmpValue = new String(ch, start, length);
    }
	
    
}
