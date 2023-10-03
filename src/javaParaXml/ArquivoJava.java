package javaParaXml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*;
import java.io.*;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;

public class ArquivoJava {
	
	public static List<String> getAttributes(JavaClass classeJava) {
	    List<String> attributeList = new ArrayList<>();
	    
		    JavaField[] fields = classeJava.getFields();
		    for (JavaField field : fields) {
		      try {
		    	  attributeList.add(field.getName());
		      }catch(Exception e){
			    	break;
			  }
		    }
		    return attributeList;
	    
	  }
	  
	  public static List<String> getMethods(JavaClass classeJava) {
	    List<String> methodList = new ArrayList<>();
	    JavaMethod[] methods = classeJava.getMethods();
	    for (JavaMethod method : methods) {
	    	try {
	    		methodList.add(method.getName());
		      }catch(Exception e){
			    	break;
			  }
	      
	    }
	    return methodList;
	  }
	
	private String caminhoAbsoluto;
	public void converterParaXml(String name) throws ClassNotFoundException, XMLStreamException, IOException {
	
	        try 
	        {
	            JavaDocBuilder builder = new JavaDocBuilder();
	            builder.addSource(new FileReader(this.getCaminhoAbsoluto()));
	            JavaClass classes[] = builder.getClasses();
	         
	            
	            int posicaoPonto = name.lastIndexOf(".");
	            String nomeSemExtensao = name.substring(0, posicaoPonto);
	           
	            
	            OutputStream outputStream = new FileOutputStream(new File(nomeSemExtensao + ".xml"));
	            System.out.println(classes.getClass().getName());
	           
                XMLOutputFactory factory = XMLOutputFactory.newInstance();
                XMLStreamWriter writer = factory.createXMLStreamWriter(outputStream);
                writer.writeStartElement("classes");
        		writer.writeCharacters("\n");
	            
	            for (JavaClass classeJava : classes)
	            {
	            	String className = "";
	            	try {
	            		className = classeJava.getName();
	            	}catch(Exception e) {
	            		break;
	            	}
	            	//Criar um for pra cada classe
	            	writer.writeStartElement("classe");
	            		writer.writeCharacters("\n");
	            		writer.writeCharacters("    ");
	            		//Escrevendo nome da classe
		            	writer.writeStartElement("nome");
		            		writer.writeCharacters(className);
		            	writer.writeEndElement();
	            		writer.writeCharacters("\n");
	                
	            	//Resgatando informações do arquivo .java
	                List<String> attributes = getAttributes(classeJava);
	                List<String> methods = getMethods(classeJava);
	                
	                //Listagem dos atributos
	                writer.writeStartElement("atributos");
	                writer.writeCharacters("\n");
		                for(String nome : attributes ) {
		                	writer.writeStartElement("atributo");
		                	writer.writeCharacters("\n");
			                	writer.writeStartElement("nome");
			                		writer.writeCharacters(nome);
			                	writer.writeEndElement();
			                	writer.writeCharacters("\n");
		                	writer.writeEndElement();
		                	writer.writeCharacters("\n");
		                }
	                writer.writeEndElement();
	                writer.writeCharacters("\n");
	                writer.writeCharacters("\n");
	                
	                //Listagem dos métodos
	                writer.writeStartElement("metodos");
	                writer.writeCharacters("\n");
	                	
		                for(String nome : methods ) {
		                	writer.writeStartElement("metodo");
		                	writer.writeCharacters("\n");
		                		writer.writeStartElement("nome");
		                			writer.writeCharacters(nome);
		                		writer.writeEndElement();
		                		writer.writeCharacters("\n");
		                	writer.writeEndElement();
		                	writer.writeCharacters("\n");
		                }
		            writer.writeEndElement();
		            writer.writeCharacters("\n");
		            writer.writeCharacters("\n");
	                
		            //Fechando tag classe
		            writer.writeEndElement();
		            writer.writeCharacters("\n");
	            }
	            writer.writeEndElement();
	            writer.writeCharacters("\n");
	            writer.flush();
	            writer.close();
	            outputStream.close();
	         
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	public String getCaminhoAbsoluto() {
		return caminhoAbsoluto;
	}
	public void setCaminhoAbsoluto(String caminhoAbsoluto) {
		this.caminhoAbsoluto = caminhoAbsoluto;
	}
}
