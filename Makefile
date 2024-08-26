IMAGE_NAME := dockerbasics
IMAGE_TAG := v0

CONTAINER_NAME := dockerbasics
CONTAINER_PORT := 8080

VOLUME_NAME := images

build:
	@docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .

rmi:
	@docker rmi $(IMAGE_NAME):$(IMAGE_TAG)

run:
	@docker volume create $(VOLUME_NAME)
	@docker run --rm -it \
	--name $(CONTAINER_NAME) \
	-p $(CONTAINER_PORT):8080 \
	-v $(VOLUME_NAME):/images \
	$(IMAGE_NAME):$(IMAGE_TAG)

stop:
	@docker stop $(CONTAINER_NAME)

history:
	@docker history $(IMAGE_NAME):$(IMAGE_TAG) \
	--no-trunc \
	--format "{{.Size}}\t{{.CreatedBy}}"

volume-inspect:
	@docker volume inspect $(VOLUME_NAME)

volume-prune:
	@docker volume prune