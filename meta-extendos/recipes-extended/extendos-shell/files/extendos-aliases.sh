# ExtendOS shell aliases
alias ll='ls -lah'
alias logs='journalctl -u extendosd -f'
alias status='systemctl status extendosd'
alias restart='systemctl restart extendosd'
alias gpu='kmscube -D /dev/dri/card1'
