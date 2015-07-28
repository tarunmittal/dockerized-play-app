FROM tarunmittal/play_2.3.9
MAINTAINER Tarun Mittal <cooltarun.86@gmail.com>
 
# Copy the relevant parts of the new app build output from the play stage command into the container
COPY bin/9tanki /app/bin/launch
COPY conf /app/conf
# Note: the base container already has the 130+MB of play jar files installed in the /app/lib folder, only copy app specific jars
# COPY lib /app/lib
 
# Application launches with /app as current directory
WORKDIR /app

# Expose http port
EXPOSE 9000
 
CMD ["/app/bin/launch"]
