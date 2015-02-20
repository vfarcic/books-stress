```bash
sudo docker build -t vfarcic/books-stress .
sudo docker run -t --rm vfarcic/books-stress
sudo docker run -t --rm \
  -e "DOMAIN=http://172.17.42.1" \
  -e "USERS=100" \
  -e "USERS_OVER_SECONDS=100" \
  -e "MAX_RESPONSE_TIME=1000" \
  vfarcic/books-stress
```