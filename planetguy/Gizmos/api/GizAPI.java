package planetguy.Gizmos.api;

import net.minecraft.block.Block;

/**
 * A class for modders to interface with Gizmos. It may be freely included in other mods, and is available
 * under the license below.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this file and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of 
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial 
 * portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author planetguy
 *
 */

public class GizAPI {
	
	/**
	 * If this is false, Gizmos is most likely not installed.
	 */
	
	public boolean isLoaded=false;
	
	/**
	 * Check if the blocks available here are null! Gizmos's banning system is simply to not load that block.
	 */
	public Block gravityBomb,accelerator,timeBomb,collider,spyLab;
	
	public GizAPI() {
		System.out.println("[Gizmos API] Loading...");
		try{
			gravityBomb=planetguy.Gizmos.ContentLoader.graviBomb;
			accelerator=planetguy.Gizmos.ContentLoader.particleAccelerator;
			timeBomb=planetguy.Gizmos.ContentLoader.timeBomb;
			collider=planetguy.Gizmos.ContentLoader.colliderCore;
			spyLab=planetguy.Gizmos.ContentLoader.spyDesk;
			isLoaded=true;
			System.out.println("[Gizmos API] Loaded successfully!");
		}catch(Exception a){
			System.out.println("[Gizmos API] Failed to load!");
		}
	}

}
