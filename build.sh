#!/bin/bash
shopt -s extglob
rm -rf reobf/minecraft
rm -f build.jar

python runtime/recompile.py "$@"
python runtime/reobfuscate.py --srgnames "$@"

cp  -r src/minecraft/!(*~|net*|cpw*|mcp|*forge*|*fml*|*ibxm*|paulscode*|*.cfg|Start.java|*mcp*) reobf/minecraft
cp  -r src/minecraft/!(*~|net*|cpw*|mcp|*forge*|*fml*|*ibxm*|paulscode*|*.cfg|Start.java|*mcp*) repo/Gizmos

rm -rf repo/Gizmos/ibxm

cd reobf/minecraft
rm -rf ixbm/
zip -r build.jar *
rm -f ~/Dropbox/Public/modstuff/gizmos_latest.jar
cp build.jar ~/Dropbox/Public/modstuff/gizmos_latest.jar

cd ../../repo/Gizmos
git add .
git commit -a
git push

