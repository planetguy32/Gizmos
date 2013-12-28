package planetguy.asmfixes;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

import planetguy.util.Debug;

import net.minecraft.entity.Entity;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TransformerNoCrosslink implements IClassTransformer{

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytecode){
		try{
			if(!transformedName.equals("net.minecraft.world.Teleporter")){
				return bytecode;
			}

			FMLDeobfuscatingRemapper remapper = FMLDeobfuscatingRemapper.INSTANCE;

			
			String nameObf="b";//1.6.4 - it's a bit of a hack, but there doesn't seem to be another method named "b".
			
			String nameDev="placeInExistingPortal";

			System.out.println("NoCrosslink: Found class!");

			name = name.replace('.', '/');

			ClassReader cr=new ClassReader(bytecode);
			ClassNode cn=new ClassNode();
			cr.accept(cn, 0);

			for(MethodNode mn: cn.methods){

				String mName=mn.name;
				if(mName.equals(nameDev)||mName.equals(nameObf)){

					InsnList il=new InsnList();

					Iterator<AbstractInsnNode> nodes=mn.instructions.iterator();

					
					while(nodes.hasNext()){
						AbstractInsnNode instr=nodes.next();
						System.out.println(instr);
					}
					
					nodes=mn.instructions.iterator();
					int index=0;
					while(nodes.hasNext()){
						
						AbstractInsnNode node=nodes.next();
	
						
						if(index==2){
							il.add(new VarInsnNode(Opcodes.ALOAD, 0));
							il.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/world/Teleporter",
									"worldServerInstance", Type.getDescriptor(WorldServer.class)));
							il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMFixesCoremod.class),
									"getMaximumRange", Type.getMethodDescriptor(ASMFixesCoremod.class.getDeclaredMethod("getMaximumRange", WorldServer.class))));

							il.add(new VarInsnNode(Opcodes.ISTORE, 9));
						}else if(index!=3){
							il.add(node);
						}
						index++;
						
						
					}
					
					mn.instructions=il;
					
					nodes=mn.instructions.iterator();
					while(nodes.hasNext()){
						AbstractInsnNode instr=nodes.next();
						System.out.println(instr);
					}

				}
			}

			//Debug.dbg("Transforming class!");

			/*128;//(short) (par1Entity.worldObj.provider.dimensionId==-1 ? 128 : 16);
			 * 
			 * In bytecode change:
			 * 
			0 sipush 128
			 *
			 *Replace with:
			 * 
			0 aload_0
			1 getfield 29
			4 invokestatic 138
			
			
			 */

			//	return cw.toByteArray();
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			cn.accept(writer);
			return writer.toByteArray();

		}catch(Exception e){
			System.out.println("!!NOCROSSLINK CRASHED!! No changes made!");
			e.printStackTrace();
		}
		return bytecode;
	}

}
