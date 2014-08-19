package me.planetguy.asmfixes;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class ASMFixesModContainer extends DummyModContainer{

	public ASMFixesModContainer(){
            super(new ModMetadata());
            ModMetadata md = super.getMetadata();
            md.modId = "planetguy_AsmFixes";
            md.name = "ASM fixes";
            md.version = "1.0";
            md.authorList = Arrays.asList(new String[]{"planetguy"});
            md.url = "https://github.com/planetguy32/Gizmos";
            md.description = "Fixes things with coremods. ";
    }
	
	@Override
	public boolean registerBus(EventBus var1, LoadController var2){
		var1.register(this);
		return true;
	}
}
