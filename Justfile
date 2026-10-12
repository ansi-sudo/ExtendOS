downloads := env_var_or_default('EXTENDOS_DOWNLOADS', '/mnt/c/Users/Admin/Downloads')
image := "extendos-dev-image"

build:
  kas-container build kas/extendos.yml
  just copy

copy:
  cp -L build/tmp/deploy/images/superbird/{{image}}-superbird-flashthing.zip {{downloads}}/
  @echo "copied to {{downloads}}"

shell:
  kas-container shell kas/extendos.yml

flash:
  flashthing-cli build/tmp/deploy/images/superbird/{{image}}-superbird-flashthing.zip

clean-build:
  rm -rf build
