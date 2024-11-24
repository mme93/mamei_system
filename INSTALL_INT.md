# Get sudo
sudo -i


sudo apt-get update
sudo apt-get install -y curl openssh-server ca-certificates tzdata perl

sudo apt-get install -y postfix

# Install dependencies
apt update; \ apt -y upgrade; \ apt install -y --no-install-recommends \ debian-archive-keyring \ curl \ gnupg \ apt-transport-https;

# Add repo
curl -L https://packages.gitlab.com/gitlab/gitlab-ce/gpgkey | apt-key add - ;\
cat <<'EOF' > /etc/apt/sources.list.d/gitlab_gitlab-ce.list
deb https://packages.gitlab.com/gitlab/gitlab-ce/ubuntu/ trusty main
deb-src https://packages.gitlab.com/gitlab/gitlab-ce/ubuntu/ trusty main
EOF

# Install gitlab
apt update; \
apt install -y gitlab-ee

#UFW for Firewall
sudo apt-get install ufw
sudo ufw enable


sudo ufw allow 22/tcp
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
