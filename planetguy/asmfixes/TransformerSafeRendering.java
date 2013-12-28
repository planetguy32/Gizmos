package planetguy.asmfixes;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.world.WorldServer;

public class TransformerSafeRendering implements IClassTransformer{

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytecode){
		try{
			if(!transformedName.equals("net.minecraft.client.renderer.RenderBlocks")){
				return bytecode;
			}

			FMLDeobfuscatingRemapper remapper = FMLDeobfuscatingRemapper.INSTANCE;

			
			String nameObf="";//TODO 1.6.4 - it's a bit of a hack, but there doesn't seem to be another method named "b".
			
			String nameDev="renderBlockByRenderType";

			System.out.println("SafeRendering: Found class!");

			name = name.replace('.', '/');

			ClassReader cr=new ClassReader(bytecode);
			ClassNode cn=new ClassNode();
			cr.accept(cn, 0);

			for(MethodNode mn: cn.methods){

				String mName=mn.name;
				if(mName.equals(nameDev)||mName.equals(nameObf)){

					InsnList il=new InsnList();
					
					mn.instructions=il;
					
				}
			}

			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			cn.accept(writer);
			return writer.toByteArray();

		}catch(Exception e){
			System.out.println("!!SAFERENDERING CRASHED!! No changes made!");
			e.printStackTrace();
		}
		return bytecode;
	}
}
