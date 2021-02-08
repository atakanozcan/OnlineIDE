# OnlineIDE

### Run on local machine
Run the following commands in the root folder of the repository:
```
bash package-all.sh
docker-compose build
docker-compose up --force-recreate
```

### Deploy to server
#### Method 1 - Via the Pipeline
Make sure there is an active gitlab runner.
Do some changes to the services you want to redeploy and push them to the master branch, or a branch called deploy.

#### Method 2 - Via SSH
Log into the VM via SSH and run the following commands:
```
cd /home/gitlab
sudo docker-compose -f docker-compose.prod.yml pull
sudo docker-compose -f docker-compose.prod.yml up --force-recreate -d
```


