package planetguy.portalmod;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

import planetguy.util.Debug;

import net.minecraft.launchwrapper.IClassTransformer;

public class NoCrosslinkASMTransformer implements IClassTransformer{

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytecode) {
		
		if(!transformedName.equals("net.minecraft.world.Teleporter")){
			return bytecode;
		}
		
		FMLDeobfuscatingRemapper remapper = FMLDeobfuscatingRemapper.INSTANCE;
		
		String signatureObf="(L"+remapper.unmap("net/minecraft/entity/Entity")+";D;D;D;F;)V";
		
		System.out.println("Found class!");
		
		name = name.replace('.', '/');
		
		ClassReader cr=new ClassReader(bytecode);
		ClassWriter cw=new ClassWriter(0);
		
		cr.accept(cw, 0);
		
		String actualName = NoCrosslinkCoremod.runtimeDeobfEnabled ? "placeInExistingPortal" : "";
		
		MethodVisitor mw=cw.visitMethod(Opcodes.ACC_PUBLIC, name, signatureObf, null, null);
		
		mw.visitCode();
		
		mw.visitVarInsn(Opcodes.ALOAD, 1);
		//mw.visitFieldInsn(Opcodes.GETFIELD, );//I dunno what to do!
		
		
		
		//Debug.dbg("Transforming class!");
		
		/*Change:
		 * 
		0 sipush 128
		 *
		 *Replace with:
		 * 
		0 aload_1
		1 getfield 138
		4 getfield 142
		7 getfield 55
		10 iconst_ml
		11 if_icmpne 9
		14 sipush 128
		17 goto 5
		20 bipush 16
		22 i2s
		*/
		
		mw.visitEnd();
		
		return cw.toByteArray();
	}

}
