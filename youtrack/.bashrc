# This script is to be manually copied to /home/<Restricted JDK User>/.bashrc for experimentation only.

# Allowed commands, hardcoded for security :)
allowed_cmds="ls cd pwd echo java -jar /vagrant/youtrack/youtrack-2020.5.2579.jar --no-browser 8084"

# Filter shell
shell_filter() {
    while true; do
        read -p "$(whoami)@$(hostname):$(pwd)> " cmd
        if [[ -z "$cmd" ]]; then
            continue
        fi
        if [[ "$allowed_cmds" == *"$cmd"* ]]; then
            eval "$cmd"
        else
            echo "Access Denied."
        fi
    done
}

# Start the restricted shell
shell_filter

# Run the following in interactive shell after logging in:
# java -jar /vagrant/youtrack/youtrack-2020.5.2579.jar --no-browser 8084

