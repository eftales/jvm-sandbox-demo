# shellcheck disable=SC2162
jps | grep MyApp > tmp
read id name <  tmp
echo $id
cd ~/IdeaProjects/sandbox/bin/
./sandbox.sh -p $id -S
cd -
rm tmp