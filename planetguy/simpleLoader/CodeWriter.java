package planetguy.simpleLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class CodeWriter {
	
	final PrintStream output;
	List<Class> classes=new ArrayList<Class>();
	
	public CodeWriter() throws IOException{
		String filename=Minecraft.getMinecraft().mcDataDir.getCanonicalPath()+"/SLGeneratedLoader.java";
		File out=new File(filename);
		FileOutputStream fos=new FileOutputStream(out);
		output=new PrintStream(fos);
	}
	
	public void addClass(Class c){
		classes.add(c);
	}
	
	public void writeLoaderClass(){
		output.println("package planetguy.gizmos.generated;");
		output.println();
		for(Class c:classes){
			output.println("import "+c.getCanonicalName()+";");
		}
		output.println();
		output.println("public class SLGeneratedLoader{");
		output.println();
		output.println("public void setupConfigs(Configuration config){");
		for(Class c:classes){
			for(Field f:c.getDeclaredFields()){
				if(f.isAnnotationPresent(SLProp.class)){
					
				}
			}
		}
		output.println("}");
		
		output.println("}");
		
	}
}
