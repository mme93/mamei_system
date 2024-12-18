FROM node:12.14-alpine AS build
WORKDIR /dist/src/app
RUN npm cache clean --force
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:latest AS ngi

COPY --from=build /app/dbmanager_fronted/dist/dbmanager_fronted /usr/share/nginx/html/dbmanager
COPY /nginx.conf  /etc/nginx/conf.d/default.conf

EXPOSE 80

