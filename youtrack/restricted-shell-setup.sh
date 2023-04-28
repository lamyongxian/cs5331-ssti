# This restricted shell is to be run using privilege account on victim server
# and used with .bash_profile to restrict Java command injection

# Create restricted user
sudo useradd -s /bin/rbash javauser
sudo mkdir /home/javauser/bin

# Grant selected binary access to restricted user
sudo ln -s /bin/java /home/javauser/bin

# Append home bin to jail restricted user
echo "PATH=$HOME/bin" >> /home/javauser/.bash_profile
sudo chown root:root /home/javauser/.bash_profile
sudo chmod 755 /home/javauser/.bash_profile

# exit and login as javauser, then run
# java -jar /vagrant/youtrack/youtrack-2020.5.2579.jar --no-browser 8084