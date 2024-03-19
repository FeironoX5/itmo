# init
mkdir lab0
cd lab0

# TASK 1

# directories
mkdir -p donphan0/roserade
mkdir -p donphan0/tirtouga
mkdir -p donphan0/salamence

mkdir -p shellos6/sunflora
mkdir -p shellos6/sudowoodo

mkdir -p swampert6/bellossom
mkdir -p swampert6/foongus

# files
touch archen4

touch donphan0/nosepass
touch donphan0/golbat
touch donphan0/pawniard

touch hippowdon8
touch ninetales7

touch shellos6/omanyte
touch shellos6/pansear

touch swampert6/piplup
touch swampert6/teddiursa

# set files
echo -e "satk=7 sdef=5 spd=7\n" > archen4
echo -e "Возможности\tOverland=3 Jump=1\nPower=3 Intelligence=4 Magnetic=0 Sinker=0\n" > donphan0/nosepass
echo -e "satk=7 sdef=8\nspd=9\n" > donphan0/golbat
echo -e "satk=4 sdef=4 spd=6\n" > donphan0/pawniard
echo -e "Ходы\tEarth Power Fire\nFang Ice Fang Iron Head Iron Tail Mud-Slap Sleep Talk Snore Stealth Rock Superpower Thunder Fang Water Pulse\n" > hippowdon8
echo -e "Тип диеты\nOmnivore\n" > ninetales7
echo -e "Тип диеты\tHerbivore\n" > shellos6/omanyte
echo -e "weigth=24.3\nheight=24.0 atk=5 def=5\n" > shellos6/pansear
echo -e "Тип покемона\tWATER\nNONE\n" > swampert6/piplup
echo -e "weigth=19.4 height=24.0 atk=8 def=5\n" > swampert6/teddiursa

# TASK 2

chmod 664 archen4
chmod 570 donphan0
chmod 066 donphan0/nosepass
chmod u=-wx,g=--x,o=-w- donphan0/roserade
chmod 357 donphan0/tirtouga
chmod u=-wx,g=rw-,o=-wx donphan0/salamence
chmod u=rw-,g=-w-,o-rwx donphan0/golbat
chmod 300 donphan0/pawniard
chmod u-rwx,g-rwx,o=r-- hippowdon8
chmod 440 ninetales7
chmod u=-wx,g=--x,o=-w- shellos6
chmod 301 shellos6/omanyte
chmod 357 shellos6/sunflora
chmod u=r-x,g=-wx,o=rwx shellos6/sudowoodo
chmod a=x shellos6/pansear
chmod 676 swampert6
chmod u=-wx,g=--x,o=-w- swampert6/bellossom
chmod 551 swampert6/foongus
chmod u=---,g=r--,o=r-- swampert6/piplup
chmod 440 swampert6/teddiursa

# TASK 3

cat hippowdon8 > shellos6/pansearhippowdon
cp hippowdon8 shellos6/sudowoodo
ln -r archen4 ./swampert6/pipluparchen
cp -r swampert6 shellos6/sunflora
cat swampert6/piplup donphan0/pawniard > archen4_89
ln -r donphan0 ./Copy_37 
ln ninetales7 swampert6/teddiursaninetales

ls -lR

# TASK 4

ws -l donphan0/golbat donphan0/pawniard shellos6/omanyte shellos6/pansear 2> /dev/null | sort -rn
ls -lR | grep "do" | sort -k2 | head -n2
cat -n ./n* ./*/n* 2> /tmp/lab0_1 | sort 
ls -lRur 2> /tmp/lab0_2 | head -n2 
ls -lRSr n* 2> /tmp/lab0_3 | head -n3 
cat  archen4 | grep -i "we"


# TASK 5
rm -f archen4
rm -f swampert6/teddiursa
rm -f swampert6/pipluparch*
rm -f swampert6/teddiursaninetal*
rm -rf swampert6
rmdir shellos6/sudowoodo




