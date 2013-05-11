#!/bin/bash
rm -rf reobf/minecraft
rm -f build.jar
python runtime/reobfuscate.py --srgnames "$@"
cp -r src/minecraft/mods reobf/minecraft/mods
cp -r src/minecraft/planetguy reobf/minecraft
cp src/minecraft/*.md reobf/minecraft
cp src/minecraft/mcmod.info reobf/minecraft
cp -r src/minecraft/textures reobf/minecraft/textures
cd reobf/minecraft
zip -r build.jar *
mv build.jar ../..
read
