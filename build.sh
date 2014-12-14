mkdir build
mkdir build/src
git clone https://github.com/AwesomeSauceMods/AwesomeSauceCore.git build/src
git clone https://github.com/AwesomeSauceMods/CoolInfoStuff.git
mv CoolInfoStuff/* build
cd build
rm -r OpenAutomation
bash gradlew build
#