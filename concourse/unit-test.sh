---
platform: linux

image_resource:
  type: docker-image
  source:
    repository: ruby
    tag: '2.1'

inputs:
- name: my-app

run:
  path: my-app/scripts/test