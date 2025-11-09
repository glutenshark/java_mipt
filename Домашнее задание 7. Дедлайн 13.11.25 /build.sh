#!/usr/bin/env bash
set -Eeuo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC="$ROOT/src/main/java"
OUT="$ROOT/out"
LIST="$ROOT/sources.lst"

mkdir -p "$OUT"
find "$SRC" -type f -name "*.java" | sort > "$LIST"
javac -encoding UTF-8 -g -d "$OUT" @"$LIST"

echo "Скомпилировано в: $OUT"
echo "Запуск (stdin): java -cp $OUT ru.mipt.hw7.Solution < input.txt"
