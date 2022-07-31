# Getting Started
in order to run the application, you need to have a postgresql database open and you need to create a database called "ioco-assessment".
remember to substitute your own db username and password to the application.yml and not my "sophisticated" ones.

after that you can run  "mvn spring-boot:run" on your terminal and the application will run and also auto create your table.

### Reference Documentation
endpoints: 

'/survivors/update' - updates the survivors latitude and longitude


'/survivors/robots/list' - gets a list of robots either flying or land. 


'/survivors/infected/flag/{idNumber}' - use this endpoint when you'd like to flag an infected survivor. The fate of the world depends on this!

'/survivors/list' - returns a list of survivors


### Docker/ Kubernetes implementation

### to build the docker container run the following command:

sudo docker build -t technical-interview-emmanuel.jar .

then finally, run this command in your terminal to start the built container on default port 5050

sudo docker run -p 5050:5050 technical-interview-emmanuel.jar

### to build the docker container run the following command:

sudo docker build -t ioco-assessment-backend-0.0.1-SNAPSHOT.jar .

then finally, run this command in your terminal to start the built container on default port 5050

sudo docker run -p 5050:5050 ioco-assessment-backend-0.0.1-SNAPSHOT.jar

------------------------------------------------------------------------------------------------

### run locally on kubernetes:
### to apply minikube and docker variables, run this on your terminal:

eval $(minikube -p minikube docker-env)

### build image once again:

docker build -t ioco-assessment-backend-0.0.1-SNAPSHOT.jar .

### create the job:

kubectl create -f kubernetes.yml

### check if it is running using:

kubectl get pods

kubectl logs emmanuel-interview-hmz4s

