# Ansible Cheatsheet: Complete Guide

## Table of Contents
1. [What is Ansible?](#what-is-ansible)
2. [How Ansible Works](#how-ansible-works)
3. [Installation](#installation)
4. [Key Concepts](#key-concepts)
5. [Ansible Commands](#ansible-commands)
6. [Playbooks](#playbooks)
7. [Modules](#modules)
8. [Inventory](#inventory)
9. [Roles](#roles)
10. [Examples](#examples)
11. [Best Practices](#best-practices)
12. [Troubleshooting](#troubleshooting)

## What is Ansible?
Ansible is an open-source automation tool used for configuration management, application deployment, task automation, and IT orchestration. It simplifies complex tasks by allowing you to define infrastructure as code, making it easy to manage servers, deploy applications, and automate repetitive processes.

- **Agentless**: No need to install software on managed nodes.
- **Idempotent**: Running the same task multiple times produces the same result.
- **Human-readable**: Uses YAML for configuration, making it easy to read and write.
- **Extensible**: Supports custom modules and plugins.

Ansible is particularly popular for DevOps, CI/CD pipelines, and cloud infrastructure management.

Ansible provides open-source automation that reduces complexity and runs everywhere. Using Ansible lets you automate virtually any task. Here are some common use cases for Ansible:

- Eliminate repetition and simplify workflows
- Manage and maintain system configuration
- Continuously deploy complex software
- Perform zero-downtime rolling updates

Ansible uses simple, human-readable scripts called playbooks to automate your tasks. You declare the desired state of a local or remote system in your playbook. Ansible ensures that the system remains in that state.

As automation technology, Ansible is designed around the following principles:

- **Agent-less architecture**: Low maintenance overhead by avoiding the installation of additional software across IT infrastructure.
- **Simplicity**: Automation playbooks use straightforward YAML syntax for code that reads like documentation. Ansible is also decentralized, using SSH with existing OS credentials to access remote machines.
- **Scalability and flexibility**: Easily and quickly scale the systems you automate through a modular design that supports a large range of operating systems, cloud platforms, and network devices.
- **Idempotence and predictability**: When the system is in the state your playbook describes, Ansible does not change anything, even if the playbook runs multiple times.

## How Ansible Works
Ansible uses a **push-based** model where the control node (your machine) pushes configurations to managed nodes via SSH (or other protocols). It doesn't require agents on the target machines.

### Architecture:
- **Control Node**: A system on which Ansible is installed. You run Ansible commands such as `ansible` or `ansible-inventory` on a control node. Can be any computer that meets the software requirements (laptops, shared desktops, servers). Multiple control nodes are possible, but Ansible itself does not coordinate across them.
- **Managed Node**: A remote system, or host, that Ansible controls. Ansible is not normally installed on managed nodes (unless using `ansible-pull`, which is rare).
- **Inventory**: A list of managed nodes provided by one or more 'inventory sources'. Your inventory can specify information specific to each node, like IP address. It is also used for assigning groups, that both allow for node selection in the Play and bulk variable assignment.
- **Playbooks**: YAML files containing plays (sets of tasks).
- **Modules**: The code or binaries that Ansible copies to and executes on each managed node (when needed) to accomplish the action defined in each Task.
- **Plugins**: Pieces of code that expand Ansible's core capabilities (connection plugins, filter plugins, callback plugins).
- **Collections**: A format in which Ansible content is distributed that can contain playbooks, roles, modules, and plugins.

### Workflow:
1. Define inventory (hosts).
2. Write playbooks with tasks.
3. Run playbooks using `ansible-playbook`.
4. Ansible connects via SSH, executes tasks, and reports results.

Ansible uses **facts** (gathered system information) to make decisions and **variables** for customization.

## Installation
Ansible can be installed on Linux, macOS, or Windows (via WSL or Cygwin). For Windows native, use WSL.

### Requirements
- **Control Node**: Any UNIX-like machine with Python 3.8+ installed (Linux, macOS, BSD, Windows WSL). Windows without WSL is not natively supported.
- **Managed Nodes**: Python to run Ansible-generated code (not required for some modules like network). SSH access with interactive POSIX shell.

### Packages
Ansible's community packages are distributed in two ways:
- `ansible-core`: Minimalist package with built-in modules and plugins.
- `ansible`: "Batteries included" package with community-curated collections.

### On Ubuntu/Debian:
```bash
sudo apt update
sudo apt install ansible
```

### On CentOS/RHEL:
```bash
sudo yum install ansible  # or dnf for newer versions
```

### On macOS (using Homebrew):
```bash
brew install ansible
```

### On Windows (WSL):
1. Install WSL (Windows Subsystem for Linux).
2. Install Ubuntu from Microsoft Store.
3. Follow Ubuntu installation steps above.

### Using pip (recommended for latest versions)
```bash
# Install pip if not available
python3 -m pip install --user pip

# Install ansible
python3 -m pip install --user ansible

# Or minimal ansible-core
python3 -m pip install --user ansible-core
```

### Using pipx (isolated environments)
```bash
pipx install --include-deps ansible
```

### Verify Installation:
```bash
ansible --version
```

### Optional: Shell Completion
Install `argcomplete` for bash/zsh completion:
```bash
python3 -m pip install --user argcomplete
# Then configure as per docs
```

## Key Concepts
- **Inventory**: List of hosts (servers) to manage.
- **Playbooks**: YAML files containing plays (sets of tasks).
- **Tasks**: Individual actions in a playbook.
- **Modules**: Code units that perform tasks (e.g., `copy`, `service`).
- **Roles**: Reusable collections of playbooks, variables, and files.
- **Facts**: Information about managed nodes (e.g., OS, IP).
- **Variables**: Dynamic values used in playbooks.
- **Handlers**: Tasks that run only when notified (e.g., restart service after config change).
- **Templates**: Jinja2 templates for dynamic file generation.

## Ansible Commands
Ansible provides several command-line tools.

### ansible
Runs ad-hoc commands on hosts.
```bash
ansible <host-pattern> -m <module> -a "<arguments>" [options]
```

Options:
- `-i <inventory>`: Specify inventory file.
- `-u <user>`: SSH user.
- `--private-key <key>`: SSH private key.
- `-b`: Become (sudo).
- `-v`: Verbose output.

Examples:
```bash
# Ping all hosts
ansible all -m ping

# Install nginx on webservers
ansible webservers -m apt -a "name=nginx state=present" -b

# Copy file
ansible all -m copy -a "src=/local/file dest=/remote/file"

# Install package ad-hoc
ansible localhost -m ansible.builtin.apt -a "name=apache2 state=present" -b -K
```

### ansible-playbook
Runs playbooks.
```bash
ansible-playbook <playbook.yml> [options]
```

Options:
- `-i <inventory>`: Inventory file.
- `--check`: Dry run (simulation).
- `--diff`: Show differences.
- `-v`: Verbose.
- `--tags <tags>`: Run only tagged tasks.
- `--skip-tags <tags>`: Skip tagged tasks.
- `-f <forks>`: Number of parallel processes.
- `-T <timeout>`: SSH timeout.
- `-u <user>`: Connection user.
- `-k`: Ask for connection password.
- `-b`: Become (sudo).
- `-K`: Ask for become password.
- `-t <tag>`: Run only tasks with this tag.
- `-M <path>`: Module path.

Example:
```bash
ansible-playbook -i /path/to/inventory -u myuser -k -f 3 -T 30 -t mytag -b -K myplaybook.yml
```

### ansible-galaxy
Manages roles and collections.
```bash
ansible-galaxy <command> [options]
```

Commands:
- `install <role>`: Install role.
- `init <role>`: Create new role.
- `list`: List installed roles.
- `collection install <collection>`: Install collection.
- `collection list`: List collections.

Examples:
```bash
ansible-galaxy install geerlingguy.nginx
ansible-galaxy collection install mynamespace.mycollection
ansible-galaxy collection install -r requirements.yml
ansible-galaxy collection list
```

### ansible-vault
Manages encrypted files.
```bash
ansible-vault <command> [options]
```

Commands:
- `create <file>`: Create encrypted file.
- `edit <file>`: Edit encrypted file.
- `view <file>`: View encrypted file.
- `encrypt <file>`: Encrypt existing file.
- `decrypt <file>`: Decrypt file.

Example:
```bash
ansible-vault create secrets.yml
```

### ansible-config
Manages Ansible configuration.
```bash
ansible-config <command> [options]
```

Example:
```bash
ansible-config dump  # Show current config
```

### ansible-doc
Shows documentation for modules/plugins.
```bash
ansible-doc <module>
```

Examples:
```bash
ansible-doc -F  # Show plugin names and files
ansible-doc -t module -l  # List modules
ansible-doc apt  # Show apt module docs
```

## Playbooks
Playbooks are YAML files defining automation tasks.

### Structure:
```yaml
---
- name: Play name
  hosts: target_hosts
  become: yes  # Optional: run as sudo
  vars:
    variable_name: value
  tasks:
    - name: Task description
      module_name:
        arg1: value1
        arg2: value2
      notify: handler_name  # Optional
  handlers:
    - name: handler_name
      service:
        name: service_name
        state: restarted
```

### Example Playbook:
```yaml
---
- name: Install and start nginx
  hosts: webservers
  become: yes
  tasks:
    - name: Update apt cache
      apt:
        update_cache: yes

    - name: Install nginx
      apt:
        name: nginx
        state: present

    - name: Start nginx
      service:
        name: nginx
        state: started
```

## Modules
Modules are the building blocks of Ansible tasks.

### Common Modules:
- **package**: Install/remove packages (`apt`, `yum`, etc.).
- **service**: Manage services.
- **copy**: Copy files.
- **template**: Copy templates with variable substitution.
- **file**: Manage files/directories.
- **user**: Manage users.
- **command/shell**: Run commands.
- **debug**: Print messages.
- **assert**: Validate conditions.

Example:
```yaml
- name: Create user
  user:
    name: john
    state: present
    groups: sudo
```

## Inventory
Inventory files list managed hosts.

### Static Inventory (INI format):
```ini
[webservers]
web1.example.com
web2.example.com

[dbservers]
db1.example.com ansible_user=admin

[all:vars]
ansible_python_interpreter=/usr/bin/python3
```

### Dynamic Inventory:
Use scripts or plugins for cloud providers (AWS, Azure, etc.).

### Inventory Variables:
```ini
web1.example.com ansible_host=192.168.1.10 ansible_user=ubuntu
```

## Roles
Roles organize playbooks into reusable components.

### Structure:
```
roles/
  myrole/
    tasks/
      main.yml
    handlers/
      main.yml
    vars/
      main.yml
    defaults/
      main.yml
    files/
    templates/
    meta/
      main.yml
```

### Using Roles:
```yaml
---
- name: Apply role
  hosts: all
  roles:
    - myrole
```

## Examples

### 1. Ad-hoc Command: Check Disk Usage
```bash
ansible all -m command -a "df -h"
```

### 2. Playbook: Deploy Web App
```yaml
---
- name: Deploy web app
  hosts: webservers
  become: yes
  vars:
    app_port: 8080
  tasks:
    - name: Install Java
      apt:
        name: openjdk-11-jdk
        state: present

    - name: Copy JAR file
      copy:
        src: app.jar
        dest: /opt/app.jar

    - name: Create systemd service
      template:
        src: app.service.j2
        dest: /etc/systemd/system/app.service

    - name: Start service
      service:
        name: app
        state: started
        enabled: yes
```

### 3. Using Variables and Loops
```yaml
---
- name: Install multiple packages
  hosts: all
  become: yes
  vars:
    packages:
      - nginx
      - git
      - curl
  tasks:
    - name: Install packages
      apt:
        name: "{{ item }}"
        state: present
      loop: "{{ packages }}"
```

### 4. Conditional Tasks
```yaml
---
- name: Conditional task
  hosts: all
  tasks:
    - name: Install httpd on RedHat
      yum:
        name: httpd
        state: present
      when: ansible_os_family == "RedHat"
```

## Best Practices
- Use version control for playbooks.
- Organize with roles for reusability.
- Use variables and facts for flexibility.
- Test playbooks with `--check` and `--diff`.
- Use ansible-vault for secrets.
- Keep playbooks idempotent.
- Use descriptive names for tasks.
- Limit privilege escalation (`become`).
- Use tags for selective execution.
- Validate YAML syntax.
- Use collections for organized content.
- Implement error handling with `ignore_errors`, `failed_when`.
- Use `serial` for rolling updates.
- Document playbooks with comments.
- Use `ansible-lint` for code quality.
- Keep inventory organized with groups.
- Use dynamic inventory for cloud environments.
- Monitor with `ansible-cmdb` or AWX/Tower.
- Use Execution Environments for containerized runs.

## Troubleshooting
- **Connection Issues**: Check SSH keys, firewall, inventory. Use `-vvv` for debug.
- **Module Errors**: Use `-v` for verbose output. Check module documentation.
- **Syntax Errors**: Validate YAML with `ansible-playbook --syntax-check`.
- **Facts Gathering**: Use `ansible -m setup <host>` to gather facts.
- **Permission Denied**: Use `become: yes` or `-b`.
- **Python Interpreter**: Set `ansible_python_interpreter` if needed.
- **SSH Issues**: Test with `ssh user@host`. Check known_hosts.
- **Module Not Found**: Ensure collections are installed.
- **Variable Issues**: Use `debug` module to print variables.
- **Idempotency Problems**: Check task conditions.
- **Performance**: Use `forks` to increase parallelism.
- **Logs**: Check `/var/log/ansible.log` on control node.
- **Collections**: Use `ansible-galaxy collection list` to verify.

### Common Errors:
- "Failed to connect": Check SSH and inventory.
- "Module not found": Ensure module is installed or spelled correctly.
- "Permission denied": Use `become: yes`.
- "Syntax Error": Check YAML indentation.
- "Undefined variable": Check variable definitions.

### Debugging Tips:
- Use `debug` module: `- debug: var=myvar`
- Run with `--step` for interactive execution.
- Use `ansible-console` for interactive testing.
- Check facts with `ansible -m setup host | grep ansible_distribution`

This cheatsheet covers the essentials of Ansible. For advanced topics, refer to the official documentation at https://docs.ansible.com.

*Content sourced from Ansible official documentation (latest stable version).* 