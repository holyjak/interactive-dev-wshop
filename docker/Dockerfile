FROM clojure:tools-deps-alpine

WORKDIR /app
COPY deps.edn .
RUN clojure -R:nrepl -Spath

EXPOSE 8088
EXPOSE 52162

CMD ["clojure", "-A:nrepl", "--bind", "0.0.0.0"]

