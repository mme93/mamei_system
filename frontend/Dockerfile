FROM node:18.12.1-alpine as build
WORKDIR /app
COPY package*.json /app/
RUN npm install n latest
RUN npm install -g @angular/cli
RUN npm install
COPY ./ /app/
EXPOSE 8100
CMD ["ng", "serve"]
