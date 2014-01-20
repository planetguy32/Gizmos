#!/bin/bash
git checkout .
shopt -s globstar
g="/g"
for line in $(cat replacements)
 do
 sedCmd='s/'$line'/g'
 echo $sedCmd
 find . -name "*.java" -print | xargs sed -i $sedCmd
done
