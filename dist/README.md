### Readme

To launch the application in a container, use the following command.  If omitted, defaults are below.

docker run -d -p 80:9000 -e DB_URL=mysql://root:password@localhost:3306/dbname  dockerusername/reponame:latest

