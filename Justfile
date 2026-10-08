build:
  kas-container build kas/extendos.yml

shell:
  kas-container shell kas/extendos.yml

flash image="extendos-dev-image":
  flashthing-cli build/tmp/deploy/images/superbird/{{image}}-superbird-flashthing.zip

clean-build:
  rm -rf build
