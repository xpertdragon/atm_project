cd ~/IdeaProjects/atm_project/src/main/kotlin
rm Atm.jar
kotlinc Atm.kt -include-runtime -d Atm.jar
if [ -f 'Atm.jar' ]; then
  clear
  java -jar Atm.jar;
else
  echo "Compilation Failed!"
fi