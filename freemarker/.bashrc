# List of allowed commands
allowed_cmds="ls cd pwd"

# Define restricted shell filtering
shell_input() {
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

# Run restricted shell
shell_input