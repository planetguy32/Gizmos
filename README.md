Gizmos
========

A Minecraft mod of the "anything goes" persuasion.

Gizmos includes:
+ several forms of explosives (planetguy.gizmos.gravitybomb, planetguy.gizmos.timebomb, planetguy.gizmos.inserter (indirectly), planetguy.gizmos.CES (not in a working state))
+ a machine to hide items in any other item (planetguy.gizmos.inserter)
+ devices to speed up and throw anything that moves (planetguy.gizmos.motiontools)
+ devices to quickly ~~lag you to death~~ remove adjacent groups trees or mining hazards. Warranty void if used in jungle biomes. (planetguy.gizmos.tool.ItemDeforester, planetguy.gizmos.tool.ItemMinersLighter)
+ a device to make machines work faster (planetguy.gizmos.tool.ItemBlockTicker)
+ a device to remove the dastardly device of destruction that is the Fork Bomb (planetguy.gizmos.tool.ItemBombDefuser)
+ machines that will quickly and painlessly switch out your ~~victim's~~ inventory (planetguy.gizmos.invUtils)
+ a fire extinguisher that works on blazes, including the fireball-flinging kind (planetguy.gizmos.tool.ItemFireExtinguisher)
+ a tool that lets you place blocks but works as a pickaxe, saving inventory space (planetguy.gizmos.tool.ItemBuildTool)
+ a block that you can reach through to use whatever is behind it (planetguy.invUtils.BlockTelekinesisCatalyst)
+ a tweak that lets modpack users raise the relative occurance of vanilla Minecraft flowers, so they aren't swamped by mod flowers (planetguy.sltweaks.FlowerFix)
+ a tweak that allows nether portals to be made in any size or shape (planetguy.sltweaks.PortalLiberator)
+ lotsa stuff that doesn't work/was a bad idea to begin with

##SimpleLoader##

Since some server administrators might want to disable some Gizmos items for various reasons, Gizmos has a rather special loading setup, SimpleLoader, which is designed to allow each item to be disabled separately (also disabling its dependencies). The loading infrastructure can be found in the package planetguy.simpleLoader.
