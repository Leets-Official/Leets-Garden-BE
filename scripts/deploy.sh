# 가동중인 awsstudy 도커 중단 및 삭제
sudo docker ps -a -q --filter "name=leetsgarden" | grep -q . && sudo docker stop leetsgarden && sudo docker rm leetsgarden | true

# 기존 이미지 삭제
sudo docker rmi l2007889/leetsgarden:1.0

# 도커허브 이미지 pull
sudo docker pull l2007889/leetsgarden:1.0

# 도커 run
sudo docker run -d -p 8080:8080 -v /home/ubuntu:/config --name leetsgarden l2007889/leetsgarden:1.0

# 사용하지 않는 불필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제되지 않습니다.
sudo docker rmi -f $(sudo docker images -f "dangling=true" -q) || true
