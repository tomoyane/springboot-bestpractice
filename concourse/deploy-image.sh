#!/bin/bash

set -e -u -x

touch ../out/version.txt
cat build.gradle | grep -E '^version.*' | sed -e 's/[^0-9.]//g' > ../out/version.txt

./gradlew build