package javaParaXml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.stream.XMLStreamException;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.Type;


public class JavaParaXML {
	
	public static void listarClasse(File arquivo) throws ClassNotFoundException, XMLStreamException, IOException {
		ArquivoJava arquivojava = new ArquivoJava();
		
		System.out.println(arquivo);
		arquivojava.setCaminhoAbsoluto(arquivo.toString());
		
		
		String name = arquivo.getName().toString();
	
		try{
			arquivojava.converterParaXml(name);
		}catch(Exception ex){
			
		}
		System.out.println("\n");
	}
	
	public static void listarArquivos(File pasta) throws ClassNotFoundException, XMLStreamException, IOException {
        File[] arquivos = pasta.listFiles(); // lista todos os arquivos e pastas no diretório atual

        for (File arquivo : arquivos) {
            if (arquivo.isFile()) { // verifica se é um arquivo
            	if(arquivo.getName().endsWith(".java")) {
            		System.out.println(arquivo.getName()); // exibe o nome do arquivo
            		listarClasse(arquivo);
            		
            		
            	}
            } else if (arquivo.isDirectory()) { // verifica se é uma pasta
                listarArquivos(arquivo); // chama o método recursivamente para percorrer a subpasta
            }
        }
    }
	
	
	public static void main(String[] args) throws ClassNotFoundException, XMLStreamException, IOException {
		
		String diretorio = "C:\\Prog II\\jedit\\src\\org\\gjt\\sp\\jedit";
        File pasta = new File(diretorio);
        if (pasta.exists()) { 
            listarArquivos(pasta);
        } else {
            System.out.println("Diretório não encontrado.");
        }
						
		}		

	}
	
	

