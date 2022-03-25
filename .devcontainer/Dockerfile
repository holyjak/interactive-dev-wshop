# # See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.205.2/containers/java
ARG VARIANT="17"
FROM mcr.microsoft.com/vscode/devcontainers/java:${VARIANT}

# # ** [Optional] Uncomment this section to install additional packages. **
# # RUN apk update \
# #     && apk add --no-cache <your-package-list-here>

# Install Clojure - see https://github.com/Quantisan/docker-clojure/blob/master/target/openjdk-14-slim-buster/tools-deps/Dockerfile
ENV CLOJURE_VERSION=1.10.3.1087
WORKDIR /tmp
RUN \
apt-get update && \
apt-get install -y curl make rlwrap wget && \
rm -rf /var/lib/apt/lists/* && \
wget https://download.clojure.org/install/linux-install-$CLOJURE_VERSION.sh && \
sha256sum linux-install-$CLOJURE_VERSION.sh && \
echo "fd3d465ac30095157ce754f1551b840008a6e3503ce5023d042d0490f7bafb98 *linux-install-$CLOJURE_VERSION.sh" | sha256sum -c - && \
chmod +x linux-install-$CLOJURE_VERSION.sh && \
./linux-install-$CLOJURE_VERSION.sh && \
clojure -e "(clojure-version)" && \
apt-get purge -y --auto-remove curl wget && \
rm ./linux-install-$CLOJURE_VERSION.sh

# Pre-cache dependencies
COPY deps.edn .
RUN clojure -R:calva -Spath

EXPOSE 8088
EXPOSE 52162

#CMD ["clojure", "-A:nrepl", "--bind", "0.0.0.0"]

#########################################
# FROM clojure:tools-deps-alpine

# WORKDIR /app
# COPY deps.edn .
# RUN clojure -R:nrepl -Spath

# EXPOSE 8088
# EXPOSE 52162

# CMD ["clojure", "-A:nrepl", "--bind", "0.0.0.0"]
