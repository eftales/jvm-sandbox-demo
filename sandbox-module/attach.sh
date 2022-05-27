# shellcheck disable=SC2162
jps | grep MyApp > tmp
read id name <  tmp
echo $id
cd ~/IdeaProjects/sandbox/bin/
./sandbox.sh -p $id -d 'my-sandbox-module/addLog'
./sandbox.sh -p $id -d 'my-sandbox-module/removeException'
./sandbox.sh -p $id -d 'my-sandbox-module/changeReturn'
cd -
rm tmp