# Docker 

## Docker Run

docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi $(docker images -aq)

docker build -t angular-nginx-multi .
docker run -p 80:80 angular-nginx-multi