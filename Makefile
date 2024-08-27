NETWORK_NAME := dockerbasics_compose_net

APP_IMAGE_NAME := dockerbasics
APP_IMAGE_TAG := v0.1
APP_CONTAINER_NAME := app
APP_CONTAINER_PORT := 8080
APP_VOLUME_NAME := images

DB_VOLUME_NAME := docker_compose_db_volume
DB_CONTAINER_NAME := db
DB_USER := user
DB_PASSWORD := password
DB_NAME := demo_db
DB_PORT := 5432


build:
	@docker build -t $(APP_IMAGE_NAME):$(APP_IMAGE_TAG) .

rmi:
	@docker rmi $(APP_IMAGE_NAME):$(APP_IMAGE_TAG)

create-net:
	@docker network create $(NETWORK_NAME)

run-db:
	@docker volume create $(DB_VOLUME_NAME)
	@docker run \
	--name $(DB_CONTAINER_NAME) \
	-d \
	--net=$(NETWORK_NAME) \
	-e POSTGRES_USER=$(DB_USER) \
	-e POSTGRES_PASSWORD=$(DB_PASSWORD) \
	-e POSTGRES_DB=$(DB_NAME) \
	-v $(DB_VOLUME_NAME):/var/lib/postgresql/data \
	postgres:16.4-alpine

stop-db:
	@docker stop $(DB_CONTAINER_NAME)

run-app:
	@docker volume create $(APP_VOLUME_NAME)
	@docker run --rm -it \
	--name $(APP_CONTAINER_NAME) \
	-p $(APP_CONTAINER_PORT):8080 \
	--net=$(NETWORK_NAME) \
	-v $(APP_VOLUME_NAME):/images \
	-e DB_HOST=$(DB_CONTAINER_NAME) \
    -e DB_PORT=$(DB_PORT) \
    -e DB_NAME=$(DB_NAME) \
    -e DB_USER=$(DB_USER) \
    -e DB_PASSWORD=$(DB_PASSWORD) \
	$(APP_IMAGE_NAME):$(APP_IMAGE_TAG)

stop-app:
	@docker stop $(APP_CONTAINER_NAME)


history:
	@docker history $(APP_IMAGE_NAME):$(APP_IMAGE_TAG) \
	--no-trunc \
	--format "{{.Size}}\t{{.CreatedBy}}"

volume-inspect:
	@docker volume inspect $(APP_VOLUME_NAME)

volume-prune:
	@docker volume prune