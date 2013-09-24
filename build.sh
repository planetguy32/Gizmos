#!/bin/bash
shopt -s extglob

cd ../..

rm -rf reobf/minecraft
rm -f build.jar

python runtime/recompile.py "$@"
python runtime/reobfuscate.py --srgnames "$@"

cd repo/Gizmos
git add .
git commit -a
git push

cd reobf/minecraft

zip -r build.jar *
rm -f ~/Dropbox/Public/modstuff/gizmos_latest.jar
cp build.jar ~/Dropbox/Public/modstuff/gizmos_latest.jar
