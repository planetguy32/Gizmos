#!/bin/bash
rm -rf reobf/minecraft
rm -f build.jar
python runtime/reobfuscate.py --srgnames "$@"
cp -r src/minecraft/mods reobf/minecraft/mods
cp -r src/minecraft/planetguy reobf/minecraft
cp src/minecraft/*.md reobf/minecraft
cp src/minecraft/mcmod.info reobf/minecraft
cp -r src/minecraft/textures reobf/minecraft/textures

cp -r src/minecraft/mods repo/Gizmos
cp -r src/minecraft/planetguy repo/Gizmos
cp src/minecraft/*.md repo/Gizmos
cp src/minecraft/mcmod.info repo/Gizmos
cp -r src/minecraft/textures repo/Gizmos

cd reobf/minecraft
zip -r build.jar *
rm -f ~/Dropbox/Public/modstuff/gizmos_latest.jar
cp build.jar ~/Dropbox/Public/modstuff/gizmos_latest.jar

cd ../../repo/Gizmos
git add .
git commit -a
git push

